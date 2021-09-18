package hulk.store.kardex.service;

import java.security.MessageDigest;
import java.util.Base64;

public class Md5Service {
    public static String hash(String plaintext) throws Exception {
        MessageDigest md = null;
        md = MessageDigest.getInstance("MD5");
        byte[] raw = null;
    	raw = md.digest(plaintext.getBytes("UTF-8"));

    	return new String (Base64.getEncoder().encode(raw));
    }

}
