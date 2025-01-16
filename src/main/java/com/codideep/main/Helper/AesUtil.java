package com.codideep.main.Helper;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
	private static final String key = "7876776653567656";
	private static final String iv = "agVbuY7ygAKikols";
	private static final String transformation = "AES/CBC/PKCS5Padding";

	private static SecretKey generateKey() throws Exception {
		return new SecretKeySpec(key.getBytes(), "AES");
	}

	private static IvParameterSpec generateIv() {
		return new IvParameterSpec(iv.getBytes());
	}

	public static String encrypt(String input) throws Exception {
		Cipher cipher = Cipher.getInstance(transformation);

		cipher.init(Cipher.ENCRYPT_MODE, generateKey(), generateIv());

		byte[] cipherText = cipher.doFinal(input.getBytes());

		return Base64.getEncoder().encodeToString(cipherText);
	}

	public static String decrypt(String cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance(transformation);

		cipher.init(Cipher.DECRYPT_MODE, generateKey(), generateIv());

		byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		
		return new String(plainText);
	}
}