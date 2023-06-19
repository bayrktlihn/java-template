package io.bayrktlihn.template.util;

import java.util.Properties;

import io.bayrktlihn.template.util.Aes;
import io.bayrktlihn.template.util.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AesTest {

	@Test
	void decrypt() {

		Properties properties = ApplicationProperties.getProperties();
		String aesKey = properties.getProperty("aes.key");
		Aes aes = new Aes(aesKey);

		String toBeEncryptedPlainText = "üğüğüşlşiişşiçöçöçöç.";
		
		String encrypted = aes.encrypt(toBeEncryptedPlainText);
		String decrypted = aes.decrypt(encrypted);

		Assertions.assertEquals(toBeEncryptedPlainText, decrypted);

	}
	
	@Test
	void generateKey() {
		String generateBase64EncodedKey = Aes.generateBase64EncodedKey();
		System.out.println(generateBase64EncodedKey);
	}

}
