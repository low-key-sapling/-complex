package com.lowkey.complex.security;

import com.lowkey.complex.entity.User;
import com.lowkey.complex.enums.LoginEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 实现UserDetailsService接口
 * 自定义查询数据库的类
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            log.info("登录用户名为空：{} 不存在", username);
            throw new UsernameNotFoundException(LoginEnum.LOGIN_USER_NAME_NOT_FOUNT.getValue());
        }
        //判断是否相等，(前端数据与数据库中的数据相比较)
        User user = new User();
        user.setUserName("user");
        user.setPassword("123");
        user.setPhone("1123123123");
        user.setLockFlag(Boolean.TRUE);
        user.setEnableFlag(Boolean.TRUE);
        user.setAccountExpiredFlag(Boolean.TRUE);
        return user;
    }
}