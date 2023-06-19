package io.bayrktlihn.template.util;

import java.util.Base64;

import io.bayrktlihn.template.util.Bytes;
import org.junit.jupiter.api.Test;

class BytesTest {

	@Test
	void create() {
		byte[] bytes = Bytes.create(32);
		String encodedBytes = Base64.getEncoder().encodeToString(bytes);
		System.out.println(encodedBytes);
	}

}
