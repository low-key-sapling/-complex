package com.lowkey.complex;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lowkey
 * @description
 * @date 2023年03月14日 14:40
 */
public class JSONTransform {

    @Test
    void testJsonToMapEntity() {
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        Map<String, User> hash = new HashMap<>();
        hash.put("user01", user);
        System.out.println(hash.get("user01").toString());
        String s = JSON.toJSONString(hash);
        System.out.println(s);
        Map<String, Object> map = JSON.parseObject(s, new TypeReference<HashMap<String, Object>>() {

        });
        System.out.println(JSON.parseObject(JSON.toJSONString(map.get("user01")), User.class).getName());
        Map<String, User> map1 = JSONUtil.toBean(s, new TypeReference<HashMap<String, User>>() {
        }, Boolean.FALSE);
        System.out.println(map1.get("user01").getAge());
    }

}

class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}