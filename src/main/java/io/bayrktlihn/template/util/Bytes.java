package io.bayrktlihn.template.util;

import java.security.SecureRandom;

public class Bytes {

	private Bytes() throws InstantiationException {
		throw new InstantiationException();
	}

	public static byte[] create(int byteLength) {
		SecureRandom secureRandom = new SecureRandom();
		byte[] bytes = new byte[byteLength];
		secureRandom.nextBytes(bytes);
		return bytes;
	}

}
