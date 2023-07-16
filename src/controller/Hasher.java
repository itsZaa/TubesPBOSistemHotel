package controller;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Hasher {
    public static String password (String pass) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(pass.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            pass = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return pass;
    }
}

