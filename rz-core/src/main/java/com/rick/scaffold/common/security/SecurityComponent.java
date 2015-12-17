package com.rick.scaffold.common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rick.scaffold.common.Constants;



@Component
public class SecurityComponent {
	
	public static final String KEY_ALGORITHM = "AES";
	
	private static Logger logger = LoggerFactory.getLogger(SecurityComponent.class);

	public int generateAuthCode() {
		int max=9999;
        int min=1000;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
	}
	
	public String encrypt(long id, String role) {
		String tokenRaw = id + Constants.STRING_SEPERATOR + role + Constants.STRING_SEPERATOR + System.currentTimeMillis();
		byte[] key = Arrays.copyOf("www.rick.com/scaffold".getBytes(), 16);
		SecretKeySpec skeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
		Cipher cipher;
		byte[] encrypted;
		try {
			cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			encrypted = cipher.doFinal(tokenRaw.getBytes());
			return bytesToHexString(encrypted);
		} catch (Exception e) {
			logger.error("加密失败", e);
			return null;
		}
	}
	
	public String decrypt(String token) {
		try {
			byte[] key = Arrays.copyOf("www.rick.com/scaffold".getBytes(), 16);
			SecretKeySpec skeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted = hexStringToBytes(token);
			byte[] original = cipher.doFinal(encrypted);
			String originalString = new String(original);
			return originalString;
		} catch (Exception e) {
			logger.error("解密失败", e);
			return null;
		}
	}
	
	
	private String bytesToHexString(byte[] src){   
	    StringBuilder stringBuilder = new StringBuilder("");   
	    if (src == null || src.length <= 0) {   
	        return null;   
	    }   
	    for (int i = 0; i < src.length; i++) {   
	        int v = src[i] & 0xFF;   
	        String hv = Integer.toHexString(v);   
	        if (hv.length() < 2) {   
	            stringBuilder.append(0);   
	        }   
	        stringBuilder.append(hv);   
	    }   
	    return stringBuilder.toString();   
	}
	
	private byte[] hexStringToBytes(String hexString) {   
	    if (hexString == null || hexString.equals("")) {   
	        return null;   
	    }   
	    hexString = hexString.toUpperCase();   
	    int length = hexString.length() / 2;   
	    char[] hexChars = hexString.toCharArray();   
	    byte[] d = new byte[length];   
	    for (int i = 0; i < length; i++) {   
	        int pos = i * 2;   
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
	    }   
	    return d;   
	}  
	
	private byte charToByte(char c) {   
	    return (byte) "0123456789ABCDEF".indexOf(c);   
	}
	
	public String sha1Hash(String origin) {
		String s = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(origin.getBytes());
			byte messageDigest[] = digest.digest();
			StringBuilder hexString = new StringBuilder();
			for(int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if(shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			s = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("Sha1 string error.", e);
		}
		return s;
	}
}
