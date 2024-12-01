package net.thevpc.samples.springnuts.core.service.impl.security;

import net.thevpc.nuts.util.NBlankable;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AppSecurityUtils {
    public static final String SECRET_PREFIX = "secret:";

    private AppSecurityUtils() {
    }

    public static String hash(String originalString) {
        MessageDigest digest = null;

        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(bytes);
        } catch (NoSuchAlgorithmException var4) {
            int u = String.valueOf(originalString).hashCode();
            return Integer.toHexString(u);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);

        for(int i = 0; i < hash.length; ++i) {
            String hex = Integer.toHexString(255 & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static String safeEncryptString(String strToEncrypt, String secret) {
        if (NBlankable.isBlank(strToEncrypt)) {
            return null;
        } else {
            return strToEncrypt.startsWith("secret:") ? strToEncrypt : "secret:" + encryptString(strToEncrypt, secret);
        }
    }

    public static String safeDecryptString(String strToDecrypt, String secret) {
        if (NBlankable.isBlank(strToDecrypt)) {
            return null;
        } else if (strToDecrypt.startsWith("secret:")) {
            String e = strToDecrypt.substring("secret:".length());
            return NBlankable.isBlank(e) ? null : decryptString(e, secret);
        } else {
            return strToDecrypt;
        }
    }

    public static String encryptString(String strToEncrypt, String secret) {
        try {
            byte[] bytes = strToEncrypt.getBytes(StandardCharsets.UTF_8);
            int v = bytes.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(v >>> 24 & 255);
            out.write(v >>> 16 & 255);
            out.write(v >>> 8 & 255);
            out.write(v >>> 0 & 255);
            out.write(bytes);

            for(int s = v + 4; s % 16 != 0; ++s) {
                out.write(0);
            }

            bytes = out.toByteArray();
            KeyInfo k = createKeyInfo(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, k.secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(bytes));
        } catch (Exception var8) {
            Exception ex = var8;
            throw new IllegalArgumentException("encryption failed", ex);
        }
    }

    public static String decryptString(String strToDecrypt, String secret) {
        if (secret != null && secret.trim().length() != 0) {
            try {
                KeyInfo k = createKeyInfo(secret);
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                cipher.init(2, k.secretKey);
                byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
                int ch1 = bytes[0] & 255;
                int ch2 = bytes[1] & 255;
                int ch3 = bytes[2] & 255;
                int ch4 = bytes[3] & 255;
                if ((ch1 | ch2 | ch3 | ch4) < 0) {
                    throw new EOFException();
                } else {
                    int v = (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
                    bytes = Arrays.copyOfRange(bytes, 4, 4 + v);
                    return new String(bytes);
                }
            } catch (Exception var10) {
                Exception ex = var10;
                throw new IllegalArgumentException("decryption failed", ex);
            }
        } else {
            throw new IllegalArgumentException("missing token");
        }
    }

    private static KeyInfo createKeyInfo(String password) {
        if (password == null || password.length() == 0) {
            password = "password";
        }

        MessageDigest sha = null;
        KeyInfo k = new KeyInfo();

        try {
            k.key = password.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-256");
            k.key = sha.digest(k.key);
            k.secretKey = new SecretKeySpec(k.key, "AES");
            return k;
        } catch (NoSuchAlgorithmException var4) {
            NoSuchAlgorithmException ex = var4;
            throw new IllegalArgumentException("encryption key building failed", ex);
        }
    }

    private static class KeyInfo {
        SecretKeySpec secretKey;
        byte[] key;

        private KeyInfo() {
        }
    }
}
