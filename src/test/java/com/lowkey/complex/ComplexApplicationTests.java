package com.lowkey.complex;

import com.lowkey.complex.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest
public class ComplexApplicationTests {
    @Autowired
    HttpServletRequest request;
    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testOSClientIPGet() {
//        List<User> all = userService.list();
//        System.out.println(all);
//        String clientAddr = OSUtil.getClientAddr(request);
//        System.out.println(clientAddr);
//        User User = new User();
//        User.setId(1);
//        System.out.println(User);
    }
}
