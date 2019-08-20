package com.spring.boot.common.util;


import com.spring.boot.common.exception.EncryptAndDecryptException;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.util.UUID;

/**
 * @author yuderen
 * @version 2019/7/12 14:41
 */
public class RSAUtil {

    // 公钥
    private Key publicKey;
    // 私钥
    private Key privateKey;

    // 指定加密算法为DESede
    private String ALGORITHM = "RSA";
    // 指定公钥存放文件
    private String PUBLIC_KEY_FILE = "PublicKey";
    // 指定私钥存放文件
    private String PRIVATE_KEY_FILE = "PrivateKey";

    public void setPUBLIC_KEY_FILE(String PUBLIC_KEY_FILE) {
        this.PUBLIC_KEY_FILE = PUBLIC_KEY_FILE;
    }

    public void setPRIVATE_KEY_FILE(String PRIVATE_KEY_FILE) {
        this.PRIVATE_KEY_FILE = PRIVATE_KEY_FILE;
    }

    /**
     * 初始化公私钥
     */
    public void init() {
        ObjectInputStream ois1 = null;
        ObjectInputStream ois2 = null;
        try {
//            ois1 = new ObjectInputStream(this.getClass().getResourceAsStream(PUBLIC_KEY_FILE));
//            ois2 = new ObjectInputStream(this.getClass().getResourceAsStream(PRIVATE_KEY_FILE));

            ois1 = new ObjectInputStream(new FileInputStream(new File(PUBLIC_KEY_FILE)));
            ois2 = new ObjectInputStream(new FileInputStream(new File(PRIVATE_KEY_FILE)));
            publicKey = (Key) ois1.readObject();
            privateKey = (Key) ois2.readObject();
        } catch (Exception e) {
            throw new EncryptAndDecryptException(e);
        } finally {
            IOUtil.close(ois1, ois2);
        }
    }

    /**
     * RSA加密
     * @param source        源数据
     * @return              加密后的数据
     */
    public String encrypt(String source) {
        try {
            // 得到Cipher对象来实现对源数据的RSA加密
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] bytes = cipher.doFinal(source.getBytes());
            return Base64Util.encode(bytes);
        } catch (Exception e) {
            throw new EncryptAndDecryptException(e);
        }
    }

    /**
     * RSA解密
     * @param cryptograph   密文
     * @return              源文
     */
    public String decrypt(String cryptograph) {
        try {
            // 得到Cipher对象对已用公钥加密的数据进行RSA解密
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] bytes = cipher.doFinal(Base64Util.decode2ByteArray(cryptograph));
            return new String(bytes);
        } catch (Exception e) {
            throw new EncryptAndDecryptException(e);
        }
    }

    public static void main(String[] args) {
        RSAUtil rsaUtil = new RSAUtil();
        rsaUtil.init();
        String pwd = "12345678";
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
//        String source = "yudr@163.com";
        String encStr = rsaUtil.encrypt(uuid);
        System.out.println("encStr --> " + encStr);

        String deStr = rsaUtil.decrypt(encStr);
        System.out.println("deStr --> " + deStr);

        String saltPwd = MD5Util.encryptMD5(pwd + uuid);
        System.out.println("saltPwd --> " + saltPwd);

        System.out.println(rsaUtil.decrypt("cbqHrzoT8VBSUFXdLZAgN7djYY08bHaeT1iB1PAEye9lv0EZn5y0A9zneUmRLbT+DqgGo+I5622lFVtGMNBbqEHPDjyNWw2eOKJgwFczrjXPzexwE9l2V8BDuQYmmmN08vyYdJR9UfY9NmnGEbeziw6NQJ9U/fSsIsEMkCLRMHw="));
    }

}
