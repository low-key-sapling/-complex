package com.lowkey.complex.util;

import cn.hutool.extra.compress.CompressUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.zip.ParallelScatterZipCreator;
import org.apache.commons.compress.archivers.zip.UnixStat;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.apache.commons.io.input.NullInputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class CompressUtils extends CompressUtil {
    private final static ExecutorService compressExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2000), new ThreadFactoryBuilder().setNameFormat("compressFileList-pool-").build());

    /**
     * 批量压缩文件 v4.0
     * 多线程压缩文件
     *
     * @param fileNameList 需要压缩的文件名称列表(包含相对路径)
     * @param zipOutName   压缩后的文件名称
     **/
    public static void compressFileList(String zipOutName, List<String> fileNameList) {
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(zipOutName));
             ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(outputStream)) {
            ParallelScatterZipCreator parallelScatterZipCreator = new ParallelScatterZipCreator(compressExecutor);
            zipArchiveOutputStream.setEncoding("UTF-8");
            for (String fileName : fileNameList) {
                File inFile = new File(fileName);
                final InputStreamSupplier inputStreamSupplier = () -> {
                    try {
                        return new FileInputStream(inFile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return new NullInputStream(0);
                    }
                };
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(inFile.getName());
                zipArchiveEntry.setMethod(ZipArchiveEntry.DEFLATED);
                zipArchiveEntry.setSize(inFile.length());
                zipArchiveEntry.setUnixMode(UnixStat.FILE_FLAG | 436);
                parallelScatterZipCreator.addArchiveEntry(zipArchiveEntry, inputStreamSupplier);
            }
            parallelScatterZipCreator.writeTo(zipArchiveOutputStream);
            log.info("ParallelCompressUtil->ParallelCompressUtil-> info:{}", JSONObject.toJSONString(parallelScatterZipCreator.getStatisticsMessage()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
