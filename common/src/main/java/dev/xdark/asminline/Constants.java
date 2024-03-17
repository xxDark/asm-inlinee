package dev.xdark.asminline;

import java.lang.invoke.MethodType;

final class Constants {

	static final MethodType BLOCK_TYPE = MethodType.methodType(Void.TYPE, AsmBlock.class);
	static final String BLOCK_TYPE_DESC = BLOCK_TYPE.toMethodDescriptorString();

	private Constants() {
	}
}
