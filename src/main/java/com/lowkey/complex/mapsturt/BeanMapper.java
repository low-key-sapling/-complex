package com.lowkey.complex.mapsturt;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// 定义忽略字段
@Mapping(target = "id", ignore = true)
@Mapping(target = "createTime", ignore = true)
@Mapping(target = "updateTime", ignore = true)
@Target(METHOD)
@Retention(RUNTIME)
@Documented
@interface IgnoreFixedField {
}

//typeConversionPolicy = ReportingPolicy.ERROR 类型不一致时抛出异常
@Mapper(typeConversionPolicy = ReportingPolicy.ERROR)
public interface BeanMapper {

    BeanMapper INSTANCE = Mappers.getMapper(BeanMapper.class);

    //浅拷贝，相当于=
    TargetData map(SourceData source);

    //忽略统一字段注解
    @IgnoreFixedField
    //忽略字段可以使用Mapping注解的ignore属性，如下：
    @Mapping(target = "id", ignore = true)
    //深拷贝
    @Mapping(target = "data", mappingControl = DeepClone.class)
    TargetData deepMap(SourceData source);

    //集合拷贝
    List<TestData> map(List<TestData> source);
}