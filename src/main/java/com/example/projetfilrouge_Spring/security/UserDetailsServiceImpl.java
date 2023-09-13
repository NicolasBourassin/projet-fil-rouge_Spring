package com.example.projetfilrouge_Spring.security;

import com.example.projetfilrouge_Spring.repository.UserRepository;
import com.example.projetfilrouge_Spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userService.findByUsernameIsContainingIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("username: " + username + " not found"));
        return user;
    }
}
