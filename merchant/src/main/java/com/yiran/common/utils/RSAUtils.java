package com.yiran.common.utils;

import org.apache.commons.codec.binary.Base64;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * * <p>
 *  * RSA公钥/私钥/签名工具包
 *  * </p>
 *  * <p>
 *  * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 *  * </p>
 *  * <p>
 *  * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 *  * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 *  * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 *  * </p>
 * 作者：许盼
 * 
 * 日期：2018/12/10-15:17
 */
public class RSAUtils {

    /** */
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /** */
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /** */
    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /** */
    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /** */
    /**
     * RSA 位数 如果采用2048 上面最大加密和最大解密则须填写:  245 256
     */
    private static final int INITIALIZE_LENGTH = 1024;

    /** */
    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(INITIALIZE_LENGTH);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param data
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64.encodeBase64String(signature.sign());
    }

    /** */
    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param data
     *            已加密数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @param sign
     *            数字签名
     *
     * @return
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64.decodeBase64(sign));
    }

    /** */
    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData
     *            已加密数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** */
    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData
     *            已加密数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    /** */
    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data
     *            源数据
     * @param publicKey
     *            公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** */
    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data
     *            源数据
     * @param privateKey
     *            私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    /** */
    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap
     *            密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }
    
    public static byte[] getCCBPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return key.getEncoded();
    }

    /** */
    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap
     *            密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }
    
    public static byte[] getCCBPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

    /**
     * 公钥加密
     */
    public static String encryptedDataOnJava(String data, String PUBLICKEY) {
        try {
            data = Base64.encodeBase64String(encryptByPublicKey(data.getBytes(), PUBLICKEY));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 私钥解密
     */
    public static String decryptDataOnJava(String data, String PRIVATEKEY) {
        String temp = "";
        try {
            byte[] rs = Base64.decodeBase64(data);
            temp = new String(RSAUtils.decryptByPrivateKey(rs, PRIVATEKEY),"UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    
    /**
     * 私钥加密数据
     * @param data
     * @param PRIVATEKEY
     * @return
     */
    public static String encryptedDataOnPrivatekey(String data, String PRIVATEKEY) {
        String temp = "";
        try {
        	
            byte[] rs = RSAUtils.encryptByPrivateKey(data.getBytes(), PRIVATEKEY);
            temp = Base64.encodeBase64String(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
 
    /**
     * 公钥解密数据
     * @param data
     * @param PUBLICKEY
     * @return
     */
    public static String decryptDataOnPublickey(String data, String PUBLICKEY) {
        try {
             byte[] rs  = Base64.decodeBase64(data);
             data = new String(RSAUtils.decryptByPublicKey(rs, PUBLICKEY));
            // data = new String(RSAUtils.decryptByPublicKey(rs, PUBLICKEY),"UTF-8");//以utf-8的方式生成字符串;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    public static void main(String[] args) throws  Exception{
    	Map<String,Object> keyMap = genKeyPair();
    	/*System.out.println("获取公钥publicKey:"+getPublicKey(keyMap));
    	System.out.println("获取私钥privateKey:"+getPrivateKey(keyMap));*/
    	
    	/*String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLMUH16tacf1pvydre5Zq+SScVeDSqlx42k/oGti1iy7UAWutJzdf+ly/D2/CwjjwQknxaFAxMht/TvEeBOq8YjHpsj/RPRBxyPu28imhKzDlO6rxcoKVpBIbTLA+q3gpB2EhNY5qJNww9VTzNaEQR5/xoLQ7+YtQgYsmPPbOvkwIDAQAB";
    	String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMsxQfXq1px/Wm/J2t7lmr5JJxV4NKqXHjaT+ga2LWLLtQBa60nN1/6XL8Pb8LCOPBCSfFoUDEyG39O8R4E6rxiMemyP9E9EHHI+7byKaErMOU7qvFygpWkEhtMsD6reCkHYSE1jmok3DD1VPM1oRBHn/GgtDv5i1CBiyY89s6+TAgMBAAECgYAhFnRXR1aLRXHEomoxwdHhCqiCpoBtBw60dIRSc3gQjnt7A1MtQYfavWPZ0QbwuTiuuivlvz4BeDUalZ5PPO8mFYh4ZHHdvTp6c4rXXl0i3j0cZEnVEJGwxK3Ds84ICKEpxcBx1uBCUy5qJsomT03Uxjz6GQZuQ693SLKHR3CugQJBAP13S8Mt95WhjEmQVC63Q/e3L+MgOzHdc72q//eUyb/FFmMXxZLow3n6owCK8TQ4hMeDeO/vBi/jDRfUuur2lGcCQQDNOUt6AoxI9SYaFV+xjnRTjJzinXED657EZi2+/j3XC4p57xne2FD6ddc7QqAaOwuvlz9PiPVFCP38ztU8SW/1AkBG+hENJuId1I8kSnZIXql5xKJ0R6JL5Gd0xV3IRrH4MYiDVzTMXsHedoEOtdm1dd44wGgtSBHDdyFDex6cjVAhAkAEpnC0YgxlttBXNPbHIjA2mHARnnmqGhOt5ZbRxhcGoWdQ3f8bEpG7KyJmYFsaXQ4lu2qe1b6hAtQmf1VLmWplAkBKmwcozbAbPXhriofxpcijeItdtBuoOznt23z5c9Q8kvnAllN+KRkqMxHqCDl+ZX11WSDArWdQDYfiCcKRurrl";
    	
    	String data = "{\"partner_id\":\"20000111\",\"version\":\"1.0\",\"_input_charset\":\"UTF-8\",\"service\":\"query_card_bin\",\"sign\":\"l+Z1C/ZEaqqvepb9IZ++VlmDn6psCVxo55SUgFOXJwxDuLZABxkCv9yGJ/+5CqnSKrvcjBSsM3WM2fjVS0b818V+nRFD/2B9ESiRXLwvbqJHg9yeqWtKzDqvwwJDgJPUPTnpFRdIoG/0FQI2Iz9wxM0Ud0bGz5AaSfO1FVyoVvs=\",\"sign_type\":\"RSA\",\"return_url\":\"http://127.0.0.1/api/yiranpay/gateway/cardBin/queryCardBin\",\"memo\":\"测试\",\"request_no\":\"2020011625632410\",\"card_no\":\"6217710808791636\",\"buss_type\":\"AUTH\"}";
    	String sign_src = SignUtils.genSignData(JSON.parseObject(data));
    	System.out.println("获取签名原串原文："+sign_src);
    	String signData = sign(sign_src.getBytes(), privateKey);
    	System.out.println("签名数据:"+signData);*/
    	/*//私钥加密
    	String ciphertext = encryptedDataOnPrivatekey(data, privateKey);
    	System.out.println("私钥加密后的密文:"+ciphertext);
    	//数据验签
    	System.out.println("验签结果"+verify(data.getBytes(), publicKey, signData));
    	//公钥解密
    	String plaintext = decryptDataOnPublickey(ciphertext, publicKey);
    	System.out.println("公钥解密明文:"+plaintext);*/
    	
    	
    	
    	
    }


}
