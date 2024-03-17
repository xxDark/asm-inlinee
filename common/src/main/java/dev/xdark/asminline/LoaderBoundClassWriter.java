package dev.xdark.asminline;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

final class LoaderBoundClassWriter extends ClassWriter {

	private final ClassLoader loader;

	public LoaderBoundClassWriter(int flags, ClassLoader loader) {
		super(flags);
		this.loader = loader;
	}

	public LoaderBoundClassWriter(ClassReader classReader, int flags, ClassLoader loader) {
		super(classReader, flags);
		this.loader = loader;
	}

	@Override
	protected ClassLoader getClassLoader() {
		return loader;
	}
}
