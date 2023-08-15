package com.lowkey.complex;

import com.google.common.collect.Maps;
import com.lowkey.complex.entity.User;
import com.lowkey.complex.util.StreamSafe;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTests {

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

    @Test
    void testStreamForEach() {
        List<User> Users = initUserList();
        //foreach 元素
        StreamSafe.of(Users).forEach(temp -> System.out.println(temp.toString()));
    }

    @Test
    void testStreamListToMap() {
        List<User> Users = initUserList();
        Map<Integer, User> UserMap = StreamSafe.of(Users).collect(Collectors.toMap(User::getId, Function.identity(), (o, n) -> n));
        // 传统的Map迭代方式
        for (Map.Entry<Integer, User> entry : UserMap.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue());
        }
        // JDK8的Map迭代方式
        UserMap.forEach((key, value) -> System.out.println(key + "：" + value));
    }

    @Test
    void testStreamListFilter() {
        List<User> Users = initUserList();
        //筛选出id为偶数的集合
        List<User> UserFilterList = StreamSafe.of(Users).filter(User -> User.getId() % 2 == 0).collect(Collectors.toList());
        StreamSafe.of(UserFilterList).forEach(temp -> System.out.println(temp.toString()));
    }

    @Test
    void testStreamListToFiledList() {
        List<User> Users = initUserList();
        //筛选出id为偶数的集合
        List<Integer> idList = StreamSafe.of(Users).map(User::getId).collect(Collectors.toList());
        System.out.println(idList);
        StreamSafe.of(idList).forEach(System.out::println);
    }

    @Test
    void testStreamListsToList() {
        //list集合合并
        List<List<User>> lists = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            lists.add(initUserList());
        }
        List<User> Users = StreamSafe.of(lists).flatMap(Collection::stream).collect(Collectors.toList());
        StreamSafe.of(Users).forEach(System.out::println);
    }

    @Test
    void testStreamListDuplicateRemoval() {
        //List去重
        List<User> Users = initUserList();
        User User = new User();
        User.setId(1);
        Users.add(User);
        System.out.println("去重前：");
        StreamSafe.of(Users).forEach(System.out::println);
        //筛选出id为偶数的集合
        List<User> duplicateRemovalList = StreamSafe.of(Users).collect(
                Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(com.lowkey.complex.entity.User::getId))), ArrayList::new)
        );
        System.out.println("去重后：");
        StreamSafe.of(duplicateRemovalList).forEach(System.out::println);
    }

    @Test
    void testStreamListToMapCustomKey() {
        //List转map，自定义key
        List<User> Users = initUserList();
        Map<String, User> UserMap = StreamSafe.of(Users).collect(Collectors.toMap(temp -> String.format("%s-%s", temp.getId(), temp.getUsername()), Function.identity(), (o, n) -> o));
        UserMap.forEach((key, value) -> System.out.printf("key:%s,value:%s%n", key, value));
    }

    @Test
    void testStreamListToMapList() {
        //List转map<T,List>，相同字段的集合
        List<User> Users = initUserList();
        User User = new User();
        User.setId(1);
        Users.add(User);
        Map<Integer, List<User>> UserListMap = StreamSafe.of(Users).collect(Collectors.toMap(com.lowkey.complex.entity.User::getId,
                e -> new ArrayList<>(Collections.singletonList(e)),
                (o, p) -> {
                    o.addAll(p);
                    return o;
                }
        ));
        UserListMap.forEach((key, value) -> System.out.printf("key:%s,value:%s%n", key, value));
    }

    @Test
    void testStreamListToGrouping() {
        List<Map<String, String>> mapList = Lists.newArrayList();
        Map<String, String> map1 = Maps.newHashMap();
        map1.put("0", "000");
        map1.put("1", "111");
        map1.put("2", "222");
        mapList.add(map1);
        Map<String, String> map2 = Maps.newHashMap();
        map2.put("0", "0000");
        map2.put("1", "1111");
        map2.put("2", "22222");
        mapList.add(map2);
        Map<String, String> map3 = Maps.newHashMap();
        map3.put("0", "00000");
        map3.put("1", "11111");

        mapList.add(map3);
        Map<String, List<Map<String, String>>> collect = mapList.stream().collect(Collectors.groupingBy(m -> m.get("0")));
        System.out.println(collect.toString());
    }
}
