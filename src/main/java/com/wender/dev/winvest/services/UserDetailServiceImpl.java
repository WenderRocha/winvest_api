package com.wender.dev.winvest.services;

import com.wender.dev.winvest.data.UserData;
import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> user = repository.findByEmail(s);

        if(user.isEmpty()){
            throw  new UsernameNotFoundException("Usuário [" + s +"] não encontrado");
        }

        return new UserData(user);
    }
}
