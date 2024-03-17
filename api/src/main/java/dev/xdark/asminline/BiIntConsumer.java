package dev.xdark.asminline;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface BiIntConsumer<U> extends BiConsumer<Integer, U> {

	default void accept(int i, U u) {
		this.accept(Integer.valueOf(i), u);
	}
}
