package dev.xdark.asminline;

import org.objectweb.asm.ConstantDynamic;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.Type;

import java.lang.invoke.MethodType;
import java.util.function.Consumer;

public interface AsmBlock {

	static void inline(Consumer<AsmBlock> blocker) {
		throw new UnsupportedOperationException("Must be replaced with bytecode");
	}

	static long inlineJ(Consumer<AsmBlock> blocker) {
		throw new UnsupportedOperationException("Must be replaced with bytecode");
	}

	static double inlineD(Consumer<AsmBlock> blocker) {
		throw new UnsupportedOperationException("Must be replaced with bytecode");
	}

	static int inlineI(Consumer<AsmBlock> blocker) {
		throw new UnsupportedOperationException("Must be replaced with bytecode");
	}

	static float inlineF(Consumer<AsmBlock> blocker) {
		throw new UnsupportedOperationException("Must be replaced with bytecode");
	}

	static char inlineC(Consumer<AsmBlock> blocker) {
		throw new UnsupportedOperationException("Must be replaced with bytecode");
	}

	static short inlineS(Consumer<AsmBlock> blocker) {
		throw new UnsupportedOperationException("Must be replaced with bytecode");
	}

	static byte inlineB(Consumer<AsmBlock> blocker) {
		throw new UnsupportedOperationException("Must be replaced with bytecode");
	}

	static boolean inlineZ(Consumer<AsmBlock> blocker) {
		throw new UnsupportedOperationException("Must be replaced with bytecode");
	}

	static <V> V inlineA(Consumer<AsmBlock> blocker) {
		throw new UnsupportedOperationException("Must be replaced with bytecode");
	}

	AsmBlock nop();

	AsmBlock $null();

	AsmBlock $int(int value);

	AsmBlock $long(long value);

	AsmBlock $float(float value);

	AsmBlock $double(double value);

	AsmBlock ldc(String value);

	AsmBlock ldc(Class<?> type);

	AsmBlock ldc(Type type);

	AsmBlock ldc(MethodType type);

	AsmBlock ldc(Handle handle);

	AsmBlock ldc(ConstantDynamic constantDynamic);

	AsmBlock iload(int idx);

	AsmBlock lload(int idx);

	AsmBlock fload(int idx);

	AsmBlock dload(int idx);

	AsmBlock aload(int idx);

	AsmBlock iaload();

	AsmBlock laload();

	AsmBlock faload();

	AsmBlock daload();

	AsmBlock aaload();

	AsmBlock baload();

	AsmBlock caload();

	AsmBlock saload();

	AsmBlock istore(int idx);

	AsmBlock lstore(int idx);

	AsmBlock fstore(int idx);

	AsmBlock dstore(int idx);

	AsmBlock astore(int idx);

	AsmBlock iastore();

	AsmBlock lastore();

	AsmBlock fastore();

	AsmBlock dastore();

	AsmBlock aastore();

	AsmBlock bastore();

	AsmBlock castore();

	AsmBlock sastore();

	AsmBlock pop();

	AsmBlock pop2();

	AsmBlock dup();

	AsmBlock dupx1();

	AsmBlock dupx2();

	AsmBlock dup2();

	AsmBlock dup2x1();

	AsmBlock dup2x2();

	AsmBlock swap();

	AsmBlock iadd();

	AsmBlock ladd();

	AsmBlock fadd();

	AsmBlock dadd();

	AsmBlock isub();

	AsmBlock lsub();

	AsmBlock fsub();

	AsmBlock dsub();

	AsmBlock imul();

	AsmBlock lmul();

	AsmBlock fmul();

	AsmBlock dmul();

	AsmBlock idiv();

	AsmBlock ldiv();

	AsmBlock fdiv();

	AsmBlock ddiv();

	AsmBlock irem();

	AsmBlock lrem();

	AsmBlock frem();

	AsmBlock drem();

	AsmBlock ineg();

	AsmBlock lneg();

	AsmBlock fneg();

	AsmBlock dneg();

	AsmBlock ishl();

	AsmBlock lshl();

	AsmBlock ishr();

	AsmBlock lshr();

	AsmBlock iushr();

	AsmBlock lushr();

	AsmBlock iand();

	AsmBlock land();

	AsmBlock ior();

	AsmBlock lor();

	AsmBlock ixor();

	AsmBlock lxor();

	AsmBlock iinc(int idx, int value);

	AsmBlock i2l();

	AsmBlock i2f();

	AsmBlock i2d();

	AsmBlock l2i();

	AsmBlock l2f();

	AsmBlock l2d();

	AsmBlock f2i();

	AsmBlock f2l();

	AsmBlock f2d();

	AsmBlock d2i();

	AsmBlock d2l();

	AsmBlock d2f();

	AsmBlock i2b();

	AsmBlock i2c();

	AsmBlock i2s();

	AsmBlock lcmp();

	AsmBlock fcmpl();

	AsmBlock fcmpg();

	AsmBlock dcmpl();

	AsmBlock dcmpg();

	// = 0
	AsmBlock ifeq(Label label);

	// != 0
	AsmBlock ifne(Label label);

	// < 0
	AsmBlock iflt(Label label);

	// >= 0
	AsmBlock ifge(Label label);

	// > 0
	AsmBlock ifgt(Label label);

	// < 0
	AsmBlock ifle(Label label);

	// [v2, v1]: v1 == v2
	AsmBlock icmpeq(Label label);

	// [v2, v1]: v1 != v2
	AsmBlock icmpne(Label label);

	// [v2, v1]: v1 < v2
	AsmBlock icmplt(Label label);

	// [v2, v1]: v1 >= v2
	AsmBlock icmpge(Label label);

	// [v2, v1]: v1 > v2
	AsmBlock icmpgt(Label label);

	// [v2, v1]: v1 <= v2
	AsmBlock icmple(Label label);

	// [v2, v1]: v1 == v2
	AsmBlock acmpeq(Label label);

	// [v2, v1]: v1 != v2
	AsmBlock acmpne(Label label);

	AsmBlock $goto(Label label);

	// TODO Maybe JSR/RET?

	AsmBlock tableswitch(int min, int max, Label dflt, Label... labels);

	AsmBlock lookupswitch(int[] keys, Label dflt, Label... labels);

	AsmBlock ireturn();

	AsmBlock lreturn();

	AsmBlock freturn();

	AsmBlock dreturn();

	AsmBlock areturn();

	AsmBlock $return();

	AsmBlock getstatic(String owner, String name, String desc);

	AsmBlock putstatic(String owner, String name, String desc);

	AsmBlock getfield(String owner, String name, String desc);

	AsmBlock putfield(String owner, String name, String desc);

	AsmBlock getstatic(Class<?> owner, String name, String desc);

	AsmBlock putstatic(Class<?> owner, String name, String desc);

	AsmBlock getfield(Class<?> owner, String name, String desc);

	AsmBlock putfield(Class<?> owner, String name, String desc);

	AsmBlock getstatic(String owner, String name, Class<?> desc);

	AsmBlock putstatic(String owner, String name, Class<?> desc);

	AsmBlock getfield(String owner, String name, Class<?> desc);

	AsmBlock putfield(String owner, String name, Class<?> desc);

	AsmBlock getstatic(Class<?> owner, String name, Class<?> desc);

	AsmBlock putstatic(Class<?> owner, String name, Class<?> desc);

	AsmBlock getfield(Class<?> owner, String name, Class<?> desc);

	AsmBlock putfield(Class<?> owner, String name, Class<?> desc);

	AsmBlock invokevirtual(String owner, String name, String desc);

	AsmBlock invokespecial(String owner, String name, String desc);

	AsmBlock invokestatic(String owner, String name, String desc);

	AsmBlock invokeinterface(String owner, String name, String desc);

	AsmBlock invokedynamic(String name, String desc, Handle bootstrap, Object... args);

	AsmBlock invokevirtual(String owner, String name, MethodType type);

	AsmBlock invokestatic(String owner, String name, MethodType type);

	AsmBlock invokeinterface(String owner, String name, MethodType type);

	AsmBlock invokevirtual(Class<?> owner, String name, String desc);

	AsmBlock invokespecial(Class<?> owner, String name, String desc);

	AsmBlock invokestatic(Class<?> owner, String name, String desc);

	AsmBlock invokevirtual(Class<?> owner, String name, MethodType type);

	AsmBlock invokespecial(Class<?> owner, String name, MethodType type);

	AsmBlock invokestatic(Class<?> owner, String name, MethodType type);

	AsmBlock invokeinterface(Class<?> owner, String name, String desc);

	AsmBlock invokeinterface(Class<?> owner, String name, MethodType type);

	AsmBlock $new(String type);

	AsmBlock $new(Class<?> type);

	AsmBlock $new(Type type);

	AsmBlock newarray(int type);

	AsmBlock newarray(String type);

	AsmBlock arraylength();

	AsmBlock athrow();

	AsmBlock checkcast(String type);

	AsmBlock $instanceof(String type);

	AsmBlock monitorenter();

	AsmBlock monitorexit();

	AsmBlock multinewarray(String desc, int dimensions);

	// v == null
	AsmBlock ifnull(Label label);

	// v != null
	AsmBlock ifnonnull(Label label);

	AsmBlock label(Label label);

	AsmBlock $try(Label start, Label end, Label handler, String type);

	AsmBlock line(int line, Label start);

	AsmBlock parameter(String name, int access);

	AsmBlock array(long[] array, int off, int len);

	AsmBlock array(double[] array, int off, int len);

	AsmBlock array(int[] array, int off, int len);

	AsmBlock array(float[] array, int off, int len);

	AsmBlock array(char[] array, int off, int len);

	AsmBlock array(short[] array, int off, int len);

	AsmBlock array(byte[] array, int off, int len);

	AsmBlock array(boolean[] array, int off, int len);

	<T> AsmBlock array(T[] array, int off, int len, BiIntConsumer<T> producer);

	AsmBlock array(long[] array);

	AsmBlock array(double[] array);

	AsmBlock array(int[] array);

	AsmBlock array(float[] array);

	AsmBlock array(char[] array);

	AsmBlock array(short[] array);

	AsmBlock array(byte[] array);

	AsmBlock array(boolean[] array);

	<T> AsmBlock array(T[] array, BiIntConsumer<T> producer);
}
