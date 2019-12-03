package com.yuuki.cooky.auth.model;

import com.yuuki.cooky.common.constant.CommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserDetailImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String username;
    private String password;
    private Integer status;
    private String perms;
    private String avatar;
    private String sex;
    private Integer deptId;
    private String deptName;
    private String email;
    private String phone;
    private String describe;
    private String roleName;


//    private List<SysRole> roleList;


    public UserDetailImpl(Integer userId, String username, String password, Integer status, String perms) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.status = status;
        this.perms = perms;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return AuthorityUtils.commaSeparatedStringToAuthorityList(this.perms);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return CommonConstant.USER_STATUS_NORMAL.intValue() == this.status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return CommonConstant.USER_STATUS_NORMAL.intValue() == this.status;
    }
}
