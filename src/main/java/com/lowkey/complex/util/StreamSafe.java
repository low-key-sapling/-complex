package com.lowkey.complex.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author yuanjifan
 * @description 安全返回stream流操作
 * @date 2022/1/25 9:56
 */
public class StreamSafe {
    public StreamSafe() {
    }

    public static <T> Stream<T> of(List<T> list) {
        return list == null ? Stream.empty() : list.stream();
    }

    public static <T> Stream<T> of(Set<T> list) {
        return list == null ? Stream.empty() : list.stream();
    }

    public static <T> Stream<T> of(T[] array) {
        return array == null ? Stream.empty() : Stream.of(array);
    }
}