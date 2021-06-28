package com.cg.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Utils {

    public static String encode(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误：" + e.getMessage(), e);
        }
    }

    public static String encode(String pwd, String salt) {
        return encode(pwd + salt);
    }

    public static boolean matches(String encode, String pwd, String salt) {
        return encode.equals(encode(pwd, salt));
    }
}
