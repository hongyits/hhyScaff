package com.huanghy.scaff.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @author huangHy
 * @description: aes加密
 * @date 2022/1/3 13:35
 */
public class AesUtil {


    public static String encStr(String key, String content) {

        byte[] keyByte = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), key.getBytes()).getEncoded();
        // 构建
        AES aes = SecureUtil.aes(keyByte);
        return aes.encryptHex(content);
    }


    public static String desStr(String key, String desContent) {
        byte[] key2 = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), key.getBytes()).getEncoded();
        AES aes2 = SecureUtil.aes(key2);
        return aes2.decryptStr(desContent);
    }


}
