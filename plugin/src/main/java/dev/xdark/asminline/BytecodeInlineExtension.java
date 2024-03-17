package dev.xdark.asminline;

import org.gradle.api.provider.Property;

public interface BytecodeInlineExtension {

	Property<Integer> getComputationFlags();
}
