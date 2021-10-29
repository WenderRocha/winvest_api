package com.wender.dev.winvest.services;

import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.repositories.UserRepository;
import com.wender.dev.winvest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: "
                + id
                + ", Type: "
                + User.class.getName()));
    }
}
