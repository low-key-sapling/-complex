package com.lowkey.complex;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonMainTest {
    public static void main(String[] args) throws Exception {
        System.out.println(method01());
        System.out.println(method02());
    }

    static String method01() {
        JSONTest jsonTest = new JSONTest();
        jsonTest.setName("lowkey");
        jsonTest.setAge("18");
        jsonTest.setName(jsonTest.getRealName());
        return JSON.toJSONString(jsonTest);
    }

    static String method02() {
        JSONTest jsonTest = new JSONTest();
        jsonTest.setAge("18");
        return JSON.toJSONString(jsonTest, SerializerFeature.WriteMapNullValue);
    }
}

class JSONTest {
    String name;
    String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @JSONField(serialize = false)
    public String getRealName() {
        try {
            return "real" + this.name.substring(0, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.name;
    }
}
