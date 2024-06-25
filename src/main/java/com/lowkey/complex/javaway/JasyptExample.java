package com.lowkey.complex.javaway;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import java.security.SecureRandom;

/**
 * @author 小工匠
 * @version 1.0
 * @mark: show me the code , change the world
 * <p>
 * http://www.jasypt.org/download.html
 */
public class JasyptExample {

    /**
     * 程序的主入口函数。
     * 该函数不接受参数，也不返回任何值。
     * 它依次调用了以下几个示例函数：
     * 1. basicExample - 展示基本示例。
     * 2. oneWayPasswordExample - 展示使用一次性密码的示例。
     * 3. changeAlgorithmExample - 展示改变算法的示例。
     * 4. multiThreadDecryptExample - 展示多线程解密的示例。
     */
    public static void main(String[] args) {
        basicExample();

        oneWayPasswordExample();

        changeAlgorithmExample();

        multiThreadDecryptExample();
    }


    /**
     * 生成一个安全的随机密码。
     * 该函数不接受任何参数。
     *
     * @return 返回一个由随机字符组成的密码字符串。密码由大写字母、小写字母和数字组成，长度为16个字符。
     */
    private static String generateSecurePassword() {
        // 创建一个安全随机数生成器
        SecureRandom random = new SecureRandom();
        // 定义密码可能包含的字符集
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        // 使用StringBuilder来构建密码字符串
        StringBuilder passwordBuilder = new StringBuilder();

        // 循环生成16个随机字符
        for (int i = 0; i < 16; i++) {
            // 从字符集中随机选择一个字符，并将其添加到密码字符串中
            passwordBuilder.append(chars[random.nextInt(chars.length)]);
        }

        System.out.println("key:" + passwordBuilder);
        // 返回构建好的密码字符串
        return passwordBuilder.toString();
    }


    /**
     * 简单文本加密示例
     * 该方法演示了如何使用BasicTextEncryptor对文本进行加密和解密。
     * 该示例不接受参数，也不返回值。
     */
    protected static void basicExample() {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

        // 使用动态生成的密钥，提高安全性
        String securePassword = generateSecurePassword();
        basicTextEncryptor.setPassword(securePassword);

        try {
            // 加密文本信息
            String encryptedText = basicTextEncryptor.encrypt("123ABCdef*");
            System.out.println("encryptedText:" + encryptedText);

            // 解密已加密的文本信息
            String decryptedText = basicTextEncryptor.decrypt(encryptedText);
            System.out.println("decryptedText:" + decryptedText);
        } catch (Exception e) {
            // 处理加密/解密过程中可能出现的异常
            System.err.println("Error during encryption or decryption: " + e.getMessage());
        }
    }


    /**
     * 展示单向密码加密的示例。
     * <p>
     * 两种方案：
     * 一种方案是把数据库中的密文解密成明文，再与用户输入的密码进行对比；
     * 另一种方案是把用户输入的密码进行加密，把加密后的密文与数据库的密文进行对比。
     * <p>
     * 第二种方案是更合理的，一方面是因为加密比解密更容易，性能更好；
     * 另一方面是减少明文出现的次数，保证安全性。
     * 第二种方案完全不需要解密，所以我们只需要单向地密码加密便可以了
     */
    private static void oneWayPasswordExample() {
        // 创建BasicPasswordEncryptor实例用于密码加密和验证
        BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
        // 加密密码 "123ABCdef*"
        String encryptedPassword = encryptor.encryptPassword("123ABCdef*");
        // 检查密码 "123ABCdef*" 是否与加密后的密码匹配，并打印结果
        System.out.println(encryptor.checkPassword("123ABCdef*", encryptedPassword));

        // 检查密码 "123AbCdef*" (小写) 是否与加密后的密码匹配，并打印结果，预期为不匹配
        System.out.println(encryptor.checkPassword("123ACbdef*", encryptedPassword));
    }


    /**
     * 该示例演示如何改变加密算法。
     * 该方法不接受参数且无返回值。
     * 主要步骤包括创建加密器、设置密码和算法、加密数据以及解密数据。
     * <p>
     * 自定义地使用不同的算法进行加密解密
     */
    private static void changeAlgorithmExample() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        System.out.println("---------");
        // 设置加密器的密码
        //encryptor.setPassword(generateSecurePassword());
        encryptor.setPassword("rQIVmVRhL7Zr2Kmu");
        // 设置加密器使用的加密算法
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        // 使用加密器对文本进行加密
        String encryptedText = encryptor.encrypt("123ABCdef*");
        System.out.println("encryptedText:" + encryptedText);

        // 使用加密器对加密后的文本进行解密
        String decryptedText = encryptor.decrypt(encryptedText);
        System.out.println("decryptedText:" + decryptedText);
    }


    /**
     * 多线程解密示例
     * 该方法演示了如何使用多线程进行加密和解密操作。
     * 注意：该方法不接受任何参数，也不返回任何值。
     * <p>
     * Jasypt提供了多线程解密操作，可以并行解密，这样可以提供更好的性能。一般建议可以设置与机器处理器核数一致的线程数进行解密
     */
    private static void multiThreadDecryptExample() {

        // 创建并配置加密器，使用池化技术以支持多线程加密
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

        // 设置线程池大小为6，即同时最多有6个线程执行加密操作
        encryptor.setPoolSize(6);

        // 设置加密使用的密码
        encryptor.setPassword(generateSecurePassword());

        // 设置加密算法
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        // 加密明文字符串
        String encryptedText = encryptor.encrypt("123ABCdef*#*");
        System.out.println("encryptedText:" + encryptedText);

        // 解密密文字符串
        String decryptedText = encryptor.decrypt(encryptedText);
        System.out.println("decryptedText:" + decryptedText);
    }


}
    