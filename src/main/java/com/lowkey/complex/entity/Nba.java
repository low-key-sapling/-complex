package com.lowkey.complex.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author yuanjifan
 * @description
 * @date 2021年11月24日 10:18
 */
@Data
@Document(indexName = "nba")
public class Nba {
    @Id
    private Integer id;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Long)
    private String age;
    @Field(type = FieldType.Text)
    private String desc;
    @Field(type = FieldType.Text)
    private String[] tags;
}
