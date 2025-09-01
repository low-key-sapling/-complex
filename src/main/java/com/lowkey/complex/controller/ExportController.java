package com.lowkey.complex.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.lowkey.complex.entity.DemoData;
import com.lowkey.complex.response.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author yuanjifan
 * @description 导出
 * @date 2023/4/28 9:23
 */
@Controller
@RequestMapping("/test/export")
public class ExportController {
    private static final Logger logger = LoggerFactory.getLogger(ExportController.class);

    public ExportController() {

    }

    @RequestMapping("/excel")
    @ResponseBody
    public ResultEntity<String> exportExcel() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 100; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }

        String fileName = "C:\\Users\\Administrator\\Downloads" + File.separator + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(list);

        return ResultEntity.successWithData("导出成功");
    }

    @RequestMapping("/excel1")
    @ResponseBody
    public ResultEntity<String> exportExcel1() {

        // 构建两行表头数据
        List<List<String>> head = new ArrayList<>();
        // 第一行标题
        head.add(Arrays.asList("姓名", "姓名", "成绩"));
        // 第二行标题（子标题）
        head.add(Arrays.asList("姓", "名", "数学"));

        // 示例数据
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("张", "三", 90));
        data.add(Arrays.asList("李", "四", 85));

        String fileName = "C:\\Users\\Administrator\\Downloads" + File.separator + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName)
                .head(head)
                .sheet("模板").doWrite(data);

        return ResultEntity.successWithData("导出成功");
    }

    @RequestMapping("/download")
    @ResponseBody
    public ResultEntity<String> downloadExcel(HttpServletResponse response) throws IOException {
        File file = new File("C:\\Users\\Administrator\\Downloads\\simpleWrite1746683730161.xlsx");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
        try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(file.toPath()));
             BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buffer = new byte[1024 * 8];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        }
        return ResultEntity.successWithData("下载完成");
    }
}