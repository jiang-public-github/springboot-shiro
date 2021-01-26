package com.shiro.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @Author jianglanglang
 * @Date 2020/11/8 14:46
 * @Description 一些基础的公共类
 */
public class BasicsUtils {

    /**
     * 获取编号（id）
     * @return
     */
    public static String uuId(){
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * base64加密
     * @param string 需要加密的字符串
     * @return
     */
    public static String base64Encoder(String string) throws UnsupportedEncodingException {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return StringUtils.isBlank(string) ? null : base64Encoder.encode(string.getBytes("utf-8"));
    }

    /**
     * base64解密
     * @param string 需要解密的字符串
     * @return
     */
    public static String base64Decoder(String string) throws IOException {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        return StringUtils.isBlank(string) ? null : new String(base64Decoder.decodeBuffer(string));
    }

    /**
     * shiro密码加密
     * @param password
     * @param salt
     * @return
     */
    public static String simpleHash(String password, String salt){
        // algorithmName：加密方式, source：明文, salt：盐：为了即使相同的密码不同的盐加密后的结果也不同防破解, hashIterations：加密次数
        SimpleHash result = new SimpleHash(Md5Hash.ALGORITHM_NAME, password, ByteSource.Util.bytes(salt), 2);
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(simpleHash("123456","admin"));
        System.out.println(simpleHash("123456","user"));
    }
}