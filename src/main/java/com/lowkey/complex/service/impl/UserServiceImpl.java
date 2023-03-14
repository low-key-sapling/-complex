package com.lowkey.complex.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lowkey.complex.entity.User;
import com.lowkey.complex.mapper.UserMapper;
import com.lowkey.complex.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lowkey
 * @since 2022-07-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
