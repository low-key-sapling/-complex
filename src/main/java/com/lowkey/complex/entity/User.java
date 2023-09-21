package com.lowkey.complex.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lowkey
 * @since 2022-07-19
 */
@Data
public class User implements Serializable, UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户名称
     */
    private String password;
    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private Address address;

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 账号是否锁定
     */
    private boolean lockFlag;
    /**
     * 账号是否启用
     */
    private boolean enableFlag;
    /**
     * 账号是否启用
     */
    private boolean accountExpiredFlag;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    //用户密码
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 账户是否未过期，过期无法验证
     */
    @Override
    public boolean isAccountNonExpired() {
        return accountExpiredFlag;
    }

    /**
     * 用户是否不被锁定，true：不被锁定,false：被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return lockFlag;
    }

    /**
     * 指示是否已过期的用户的凭据(密码)，过期的凭据防止认证
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return accountExpiredFlag;
    }

    /**
     * 用户是否被启用或禁用。禁用的用户无法进行身份验证。
     */
    @Override
    public boolean isEnabled() {
        return enableFlag;
    }

    /**
     * 认证完成后，擦除密码
     */
    @Override
    public void eraseCredentials() {
        this.setPassword(null);
    }
}
