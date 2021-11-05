package com.wender.dev.winvest.services;

import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.repositories.UserRepository;
import com.wender.dev.winvest.services.exceptions.DataIntegratyViolationException;
import com.wender.dev.winvest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: " + id));
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public User create(User obj){

        if(findByCPF(obj) != null){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        if(findByEMAIL(obj) != null){
            throw new DataIntegratyViolationException("E-mail já cadastrado na base de dados!");
        }
        return repository.save(new User(obj.getName(), obj.getEmail(), obj.getPhone(), obj.getCpf(), obj.getPassword())) ;
    }

    private User findByCPF(User obj){
        User user = repository.findByCPF(obj.getCpf());
        if(user != null){
            return user;
        }

        return null;
    }

    private User findByEMAIL(User obj){
        User user = repository.findByEMAIL(obj.getEmail());

        if(user != null){
            return user;
        }

        return null;
    }

}
