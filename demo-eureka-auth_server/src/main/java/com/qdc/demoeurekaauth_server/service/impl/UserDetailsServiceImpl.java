package com.qdc.demoeurekaauth_server.service.impl;

import com.qdc.demoeurekaauth_server.pojo.Role;
import com.qdc.demoeurekaauth_server.pojo.User;
import com.qdc.demoeurekaauth_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user=userService.getUser(username);
        System.out.println(user+"aaaaa");
        if(user==null || user.getId()<1){
            throw new UsernameNotFoundException("username not found"+username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                true,true,true,true,getGrantAuthorities(user));

    }

    private Collection<? extends GrantedAuthority> getGrantAuthorities(User user) {
        Set<Role> roles= new HashSet<>();
        Role r=new Role(1,"admin");
        roles.add(r);
        user.setRoles(roles);

        Set<GrantedAuthority> authorities=new HashSet<>();
        for (Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE"+role.getName()));
            System.out.println(role);

        }
        return authorities;
    }


}
