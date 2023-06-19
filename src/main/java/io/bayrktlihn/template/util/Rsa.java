package io.bayrktlihn.template.util;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class Rsa {

	private byte[] privateKeyBytes;
	private byte[] publicKeyBytes;

	public Rsa(String base64EncodedPrivateKey, String base64EncodedPublicKey) {
		privateKeyBytes = Base64.getDecoder().decode(base64EncodedPrivateKey);
		publicKeyBytes = Base64.getDecoder().decode(base64EncodedPublicKey);
	}

	public String encrypt(String plainText) {
		try {

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public String decrypt(String encrypted) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

			byte[] base64DecodedEncryptedBytes = Base64.getDecoder().decode(encrypted);

			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedBytes = cipher.doFinal(base64DecodedEncryptedBytes);
			return new String(decryptedBytes, StandardCharsets.UTF_8);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static KeyPair generateKeyPair(int bitLength) {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(bitLength);
			return keyPairGenerator.generateKeyPair();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
