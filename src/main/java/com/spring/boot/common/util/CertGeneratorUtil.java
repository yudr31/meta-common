package com.spring.boot.common.util;

import com.spring.boot.common.exception.EncryptAndDecryptException;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

/**
 * RSA证书生成器
 * @author yuderen
 * @version 2019/7/12 15:03
 */
public class CertGeneratorUtil {

    // 指定加密算法为DESede
    private static String ALGORITHM = "RSA";
    // 指定key的大小
    private static int KEYSIZE = 1024;
    // 指定公钥存放文件
    private static String PUBLIC_KEY_FILE = "PublicKey";
    // 指定私钥存放文件
    private static String PRIVATE_KEY_FILE = "PrivateKey";

    private static void generateKeyPair() {
        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            // RSA算法要求有一个可信任的随机数源
            SecureRandom secureRandom = new SecureRandom();
            // 为RSA算法创建一个KeyPairGenerator对象
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            // 利用上面的随机数据源初始化这个KeyPairGenerator对象
            keyPairGenerator.initialize(KEYSIZE, secureRandom);
            // 生成密匙对
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            // 得到公钥
            Key publicKey = keyPair.getPublic();
            // 得到私钥
            Key privateKey = keyPair.getPrivate();

            // 用对象流将生成的密钥写入文件
            oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
            oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
            oos1.flush();
            oos2.flush();
        } catch (Exception e) {
            throw new EncryptAndDecryptException(e);
        } finally {
            IOUtil.close(oos1, oos2);
        }
    }

    public static void main(String[] args) {
        generateKeyPair();
    }

}
