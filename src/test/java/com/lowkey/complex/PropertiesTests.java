package com.lowkey.complex;

import com.lowkey.complex.entity.User;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.stream.Stream;

public class PropertiesTests {
    List<User> initUserList() {
        List<User> list = Lists.newArrayList();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        //foreach 下标
        Stream.iterate(0, i -> i + 1).limit(list.size()).forEach(i -> {
            list.get(i).setId(i);
            list.get(i).setUserName("user name - " + i);
        });
        return list;
    }
}
