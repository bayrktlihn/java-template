package io.bayrktlihn.template.util;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Properties;

import io.bayrktlihn.template.util.ApplicationProperties;
import io.bayrktlihn.template.util.Rsa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RsaTest {

	@Test
	void decrypt() {
		Properties properties = ApplicationProperties.getProperties();
		String base64EncodedPrivateKey = (String) properties.get("rsa.private_key");
		String base64EncodedPublicKey = (String) properties.get("rsa.public_key");
		Rsa rsa = new Rsa(base64EncodedPrivateKey, base64EncodedPublicKey);

		String toBeEncrypted = "$ğüşiöç";

		String encrypted = rsa.encrypt(toBeEncrypted);
		String decrypted = rsa.decrypt(encrypted);

		Assertions.assertEquals(toBeEncrypted, decrypted);
	}

	@Test
	void generateKeyPair() {
		KeyPair keyPair = Rsa.generateKeyPair(2048);
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();

		byte[] encodedPrivateKey = privateKey.getEncoded();
		byte[] encodedPublicKey = publicKey.getEncoded();

		String base64EncodedPublicKey = Base64.getEncoder().encodeToString(encodedPublicKey);
		String base64EncodedPrivateKey = Base64.getEncoder().encodeToString(encodedPrivateKey);

		System.out.println(base64EncodedPrivateKey);
		System.out.println(base64EncodedPublicKey);
	}

}
