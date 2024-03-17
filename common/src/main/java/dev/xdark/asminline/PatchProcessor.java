package dev.xdark.asminline;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class PatchProcessor {
	private final int writeFlags;

	public PatchProcessor(int writeFlags) {
		this.writeFlags = writeFlags;
	}

	public byte[] processBytes(ClassLoader loader, byte[] bytes) {
		ClassReader reader = new ClassReader(bytes);
		Map<MethodInfo, ClassWriter> methods = new HashMap<>();
		reader.accept(new BlocksCollector(methods), 0);
		if (methods.isEmpty()) {
			return bytes;
		}
		ClassWriter rewriter = new ClassWriter(reader, 0);
		AsmInliner inliner = new AsmInliner(rewriter, loader, methods);
		reader.accept(inliner, 0);
		if (inliner.rewrite) {
			ClassWriter writer = new LoaderBoundClassWriter(writeFlags, loader);
			AsmUtil.copySymbolTable(rewriter, writer);
			new ClassReader(rewriter.toByteArray()).accept(writer, 0);
			return writer.toByteArray();
		}
		return bytes;
	}

	public void processDirectoryTree(ClassLoader loader, Path dir) throws IOException {
		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				FileVisitResult result = super.visitFile(file, attrs);
				if (file.getFileName().toString().endsWith(".class")) {
					byte[] bytes = Files.readAllBytes(file);
					byte[] rewrite = processBytes(loader, bytes);
					if (rewrite != bytes) {
						Files.write(file, rewrite, StandardOpenOption.TRUNCATE_EXISTING);
					}
				}
				return result;
			}
		});
	}

	public void processFileSystem(ClassLoader loader, FileSystem fs) throws IOException {
		processDirectoryTree(loader, fs.getPath("/"));
	}

	public void processURI(ClassLoader loader, URI uri) throws IOException {
		try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
			processFileSystem(loader, fs);
		}
	}

	public void processArchive(ClassLoader loader, Path archive) throws IOException {
		processURI(loader, archive.toUri());
	}

	public void processArchive(ClassLoader loader, File archive) throws IOException {
		processURI(loader, archive.toPath().toUri());
	}
}
