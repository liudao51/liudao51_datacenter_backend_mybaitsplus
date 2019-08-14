package com.liudao51.datacenter.core.util;


import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class ShiroUtil {

    public static final String ALGORITHM_NAME = "MD5"; //散列算法,这里使用MD5算法
    public static final int HASH_ITERATIONS = 1024;    //散列次数, 如:散列两次(相当于md5(md5("")))

    public static String encryptPassword(String val, String salt) {
        Hash simpleHash = new SimpleHash(ALGORITHM_NAME, val, ByteSource.Util.bytes(salt), HASH_ITERATIONS);

        return simpleHash.toHex();
    }
}
