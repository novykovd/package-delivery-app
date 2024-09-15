package com.example.delivery_router_project.securityconfig;

import com.example.delivery_router_project.entities.AccountEntity;
import com.example.delivery_router_project.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AccountEntity account = repository.findByName(username);
        return User.builder().username(account.getUsername()).password(account.getPassword()).roles(account.getType()).passwordEncoder((rawPass) -> passwordEncoder.encode(rawPass)).build();
    }


}
