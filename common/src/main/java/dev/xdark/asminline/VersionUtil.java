package dev.xdark.asminline;

final class VersionUtil {

	static final boolean java9;

	private VersionUtil() {
	}

	static {
		boolean j9;
		try {
			Class.forName("jdk.internal.reflect.MagicAccessorImpl", true, null);
			Class.forName("jdk.internal.misc.Unsafe", true, null);
			j9 = true;
		} catch (ClassNotFoundException ignored) {
			j9 = false;
		}
		java9 = j9;
	}
}
