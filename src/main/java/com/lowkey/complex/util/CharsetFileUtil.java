package com.lowkey.complex.util;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * 文件编码工具类
 */
public class CharsetFileUtil {
    /**
     * 获取文件编码
     *
     * @param fileName 文件名称
     * @return 文件编码
     */
    public static String getFileCharacter(String fileName) {
        byte[] buf = new byte[4096];
        try (InputStream inputStream = Files.newInputStream(Paths.get(fileName))) {
            UniversalDetector detector = new UniversalDetector();

            int nread;
            while ((nread = inputStream.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();

            String encoding = detector.getDetectedCharset();
            detector.reset();
            if (Objects.isNull(encoding)) {
                throw new IllegalArgumentException("No encoding detected.");
            }
            return encoding;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
