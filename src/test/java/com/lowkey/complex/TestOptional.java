package com.lowkey.complex;

import com.lowkey.complex.entity.Address;
import com.lowkey.complex.entity.Country;
import com.lowkey.complex.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author yuanjifan
 * @description Test Optional
 * @date 2022年08月18日 16:08
 */
public class TestOptional {

    /**
     * 同一个对象的多层判空,不为空进行操作
     */
    @Test
    void isObjectNull() {
        User user = new User();
        //传统判空
        if (user != null) {
            Address addr = user.getAddress();
            if (addr != null) {
                Country country = addr.getCountry();
                if (country != null) {
                    String city = country.getName();
                    if (city != null) {
                        // ....
                    }
                }
            }
        }

        //Optional判空,不为空进行操作
        Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getCountry)
                .map(Country::getName)
                .ifPresent(countyName -> {
                    //不等于空,进行操作
                    System.out.println("countyName is not null");
                });
    }

    /**
     * 针对空值返回默认值
     */
    @Test
    void setDefault() {
        User user = new User();
        Address address = new Address();
        address.setCountry(new Country());
        user.setAddress(address);
        //传统判空
        if (user != null) {
            Address addr = user.getAddress();
            if (addr != null) {
                Country country = addr.getCountry();
                if (country != null) {
                    String city = country.getName();
                    if (city != null) {
                        System.out.println("country is not null");
                    }
                }
            }
        }

        //Optional判断其中一环只要为空返回默认值
        String countyName = Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getCountry)
                .map(Country::getName)
                .orElse("默认值");
        System.out.println("countyName default：" + countyName);
        System.out.println("countyName：" + user.getAddress().getCountry().getName());
    }


    //创建Optional的方式#
    //1. Optional.of(T)#
    //通过一个不为空的值构造一个Optional对象.
    //
    //public static <T> Optional<T> of(T value) {
    //    return new Optional<>(Objects.requireNonNull(value));
    //}
    //2. Optional.ofNullable(T)#
    //通过一个可以为空的值构造一个Optional对象.
    //
    //内部值为null的Optional实例只有一个: Optional内的Optional.EMPTY对象.
    //
    //public static <T> Optional<T> ofNullable(T value) {
    //    return value == null ? (Optional<T>) EMPTY : new Optional<>(value);
    //}
    //3. Optional.empty()#
    //返回值为null的Optional对象
    //
    //public static<T> Optional<T> empty() {
    //    @SuppressWarnings("unchecked")
    //    Optional<T> t = (Optional<T>) EMPTY;
    //    return t;
    //}
}
