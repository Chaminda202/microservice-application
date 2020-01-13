package com.springboot.rentcar.authserver.service;

import com.springboot.rentcar.authserver.model.AuthUserDetails;
import com.springboot.rentcar.authserver.model.User;
import com.springboot.rentcar.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> userObject = this.userRepository.findByUsername(name);
        userObject.orElseThrow(() -> new UsernameNotFoundException("Username or password invalid"));
        UserDetails userDetails = new AuthUserDetails(userObject.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
}
