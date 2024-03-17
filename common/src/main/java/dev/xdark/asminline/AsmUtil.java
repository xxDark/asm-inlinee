package dev.xdark.asminline;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;

final class AsmUtil {

	private static final MethodHandle MH_GET_SYMBOL_TABLE;
	private static final MethodHandle MH_SET_SYMBOL_TABLE;
	private static final MethodHandle MH_SET_FIRST_METHOD;

	private AsmUtil() {
	}

	static Object getSymbolTable(ClassWriter writer) {
		try {
			return MH_GET_SYMBOL_TABLE.invoke(writer);
		} catch (Throwable t) {
			throw new AssertionError(t);
		}
	}

	static void copySymbolTable(ClassWriter source, ClassWriter target) {
		try {
			MH_SET_SYMBOL_TABLE.invoke(target, MH_GET_SYMBOL_TABLE.invoke(source));
		} catch (Throwable t) {
			throw new AssertionError(t);
		}
	}

	static void setSymbolTable(ClassWriter writer, Object symbolTable) {
		try {
			MH_SET_SYMBOL_TABLE.invoke(writer, symbolTable);
		} catch (Throwable t) {
			throw new AssertionError(t);
		}
	}

	static void setFirstMethod(ClassWriter writer, MethodVisitor method) {
		try {
			MH_SET_FIRST_METHOD.invoke(writer, method);
		} catch (Throwable t) {
			throw new AssertionError(t);
		}
	}

	static {
		try {
			Class<?> symbolTable = Class.forName("org.objectweb.asm.SymbolTable");
			Class<?> methodWriter = Class.forName("org.objectweb.asm.MethodWriter");
			Lookup lookup = LookupUtil.lookup();
			MH_GET_SYMBOL_TABLE = lookup.findGetter(ClassWriter.class, "symbolTable", symbolTable);
			MH_SET_SYMBOL_TABLE = lookup.findSetter(ClassWriter.class, "symbolTable", symbolTable);
			MH_SET_FIRST_METHOD = lookup.findSetter(ClassWriter.class, "firstMethod", methodWriter);
		} catch (IllegalAccessException | ClassNotFoundException | NoSuchFieldException ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
}
