package io.bayrktlihn.template.util;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Aes {

	private byte[] keyBytes;

	public Aes(String encodedBase64Key) {
		keyBytes = Base64.getDecoder().decode(encodedBase64Key);
	}

	public String encrypt(String plainText) {

		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String decrypt(String base64EncryptedKey) {

		try {

			byte[] decodedBase64EncryptedKey = Base64.getDecoder().decode(base64EncryptedKey);

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] decodedBytes = cipher.doFinal(decodedBase64EncryptedKey);
			return new String(decodedBytes, StandardCharsets.UTF_8);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String generateBase64EncodedKey() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey generateKey = keyGenerator.generateKey();
			byte[] encoded = generateKey.getEncoded();
			return Base64.getEncoder().encodeToString(encoded);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
