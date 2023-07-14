package com.lowkey.complex.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lowkey.complex.entity.User;
import com.lowkey.complex.mapper.UserMapper;
import com.lowkey.complex.service.IUserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lowkey
 * @since 2022-07-19
 */
@Service
@CacheConfig(cacheNames = "caffeineCacheManager")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    @CachePut(key = "#entity.id")
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(key = "#entity.id")
    public boolean removeById(User entity) {
        return super.removeById(entity);
    }

    @Override
    @CachePut(key = "#entity.id")
    public boolean updateById(User entity) {
        return super.updateById(entity);
    }

    @Override
    @Cacheable(key = "#id")
    public User getById(Serializable id) {
        return super.getById(id);
    }
}
