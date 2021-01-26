package com.shiro;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;

/**
 * @Author jianglanglang
 * @Date 2020/12/15 14:10
 * @Description
 */
public class JasyptTest {

    @org.junit.Test
    public void jasyptEncrypt() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWITHSHA1ANDRC2_40");          // 加密的算法
        config.setPassword("00AA1789cee32A4482da531e72669788");       // 加密的密钥
        standardPBEStringEncryptor.setConfig(config);

        System.out.println("加密："+standardPBEStringEncryptor.encrypt("admin"));
        System.out.println("\n");
        System.out.println("加密："+standardPBEStringEncryptor.encrypt("admin123456"));

    }

    @org.junit.Test
    public void jasyptDecoder() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWITHSHA1ANDRC2_40");
        config.setPassword("00AA1789cee32A4482da531e72669788");
        standardPBEStringEncryptor.setConfig(config);

        System.out.println("解密："+standardPBEStringEncryptor.decrypt("0w+gRlQmOQgL5J5ilCSjMg=="));
        System.out.println("\n");
        System.out.println("解密："+standardPBEStringEncryptor.decrypt("RtjzSw1FdztvdKV2INKrfw=="));

    }


}