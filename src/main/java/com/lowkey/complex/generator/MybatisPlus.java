package com.lowkey.complex.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author yuanjifan
 * @description
 * @date 2022年07月19日 15:44
 */
public class MybatisPlus {
    public static void main(String[] args) {
        System.out.println(1);
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/complex", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("lowkey") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\WorkSpace\\IdeaWorkSpace\\complex\\src\\main\\java\\com\\lowkey\\complex"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\WorkSpace\\IdeaWorkSpace\\complex\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
