package fr.fouilleOpot.security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class EncryptDecryptURL {
    private static String algorithm = "AES";
    private static SecretKeySpec secretKey = new SecretKeySpec("TheVerySecretK3Y".getBytes(),algorithm);

    public static String encrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return new String(Base64.getUrlEncoder().encode(encryptedData));
    }

    public static String decrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {

        byte[] decryptedData = Base64.getUrlDecoder().decode(data);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String( cipher.doFinal(decryptedData));
    }

    public static Long checksum(String string) {
        long checksum = 0;
        for (int i = 0; i < string.length(); i++) {
            checksum += i * string.charAt(i);
        }
        return checksum;
    }

    public static Map<String, String> buildMap(String code) {
        if (code == null)
            return null;
        String[] params = code.split("&");
        Map<String, String> map = new HashMap<>();
        for(String el : params){
            String[] test = el.split("=");
            String name = test[0];
            String value = test[1];
            map.put(name, value);
        }
        return map;
    }
}
