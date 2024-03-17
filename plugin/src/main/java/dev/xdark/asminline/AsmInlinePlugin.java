package dev.xdark.asminline;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.tasks.compile.JavaCompile;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class AsmInlinePlugin implements Plugin<Project> {
	@Override
	public void apply(Project project) {
		BytecodeInlineExtension extension = project.getExtensions().create("bytecodeInlining", BytecodeInlineExtension.class);
		project.getTasks().withType(JavaCompile.class).configureEach(task -> {
			task.doLast(__ -> {
				PatchProcessor patchProcessor = new PatchProcessor(extension.getComputationFlags().get());
				DirectoryProperty destinationDirectory = task.getDestinationDirectory();
				URL[] urls = Stream.concat(
						task.getClasspath().getFiles().stream(),
						Stream.of(destinationDirectory.getAsFile().get())
				).map(file -> {
					try {
						return file.toURI().toURL();
					} catch (MalformedURLException e) {
						throw new UncheckedIOException(e);
					}
				}).toArray(URL[]::new);
				try (URLClassLoader cl = URLClassLoader.newInstance(urls, getClass().getClassLoader())) {
					for (File file : destinationDirectory.getAsFileTree()) {
						Path path = file.toPath();
						byte[] b = Files.readAllBytes(path);
						byte[] newBytes = patchProcessor.processBytes(cl, b);
						if (b != newBytes) {
							Files.write(path, newBytes);
						}
					}
				} catch (IOException ex) {
					throw new UncheckedIOException("Failed bytecode instrumentation", ex);
				}
			});
		});
	}
}
