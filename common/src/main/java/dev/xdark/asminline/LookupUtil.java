package dev.xdark.asminline;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Field;

public final class LookupUtil {

	private static final Lookup LOOKUP;

	private LookupUtil() {
	}

	public static Lookup lookup() {
		return LOOKUP;
	}

	static {
		try {
			Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
			field.setAccessible(true);
			LOOKUP = (Lookup) field.get(null);
		} catch (IllegalAccessException | NoSuchFieldException ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
}
