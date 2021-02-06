package com.yuuki.cooky.auth.service;

import com.yuuki.cooky.auth.feign.MenuService;
import com.yuuki.cooky.auth.feign.UserService;
import com.yuuki.cooky.auth.model.UserDetailImpl;
import com.yuuki.cooky.common.model.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookyUserDetailsService implements UserDetailsService {


    final PasswordEncoder passwordEncoder;
    final UserService userService;
    final MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVo userVo = userService.findByUserName(username);
        if(null != userVo){
            String perms = menuService.findPermsByUser(userVo.getUsername());
            UserDetailImpl userDetail = new UserDetailImpl(userVo.getId()
                    , userVo.getUsername()
                    , userVo.getPassword()
                    , userVo.getStatus()
                    , perms);
            BeanUtils.copyProperties(userVo,userDetail);
            return userDetail;
        }else {
            throw new UsernameNotFoundException("");
        }

    }
}
