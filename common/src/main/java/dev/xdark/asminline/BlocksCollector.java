package dev.xdark.asminline;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Modifier;
import java.util.Map;

final class BlocksCollector extends ClassVisitor {

	private final Map<MethodInfo, ClassWriter> writers;
	private int version;
	private String name;

	public BlocksCollector(
			Map<MethodInfo, ClassWriter> writers) {
		super(Opcodes.ASM9);
		this.writers = writers;
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName,
	                  String[] interfaces) {
		this.version = version;
		this.name = name;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
	                                 String[] exceptions) {
		if (Modifier.isStatic(access) && Constants.BLOCK_TYPE_DESC.equals(descriptor)) {
			ClassWriter writer = new ClassWriter(0);
			writer.visit(version, Opcodes.ACC_PUBLIC,
					this.name + '$' + name + '$' + System.currentTimeMillis(), null,
					"java/lang/Object", null);
			MethodVisitor proxy = writer.visitMethod(access, name, descriptor, signature, exceptions);
			writers.put(new MethodInfo(name, descriptor), writer);
			return proxy;
		}
		return null;
	}
}
