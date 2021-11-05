package com.wender.dev.winvest.services;

import com.wender.dev.winvest.entities.Wallet;
import com.wender.dev.winvest.repositories.WalletRepository;
import com.wender.dev.winvest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository repository;

    public List<Wallet> findAll(){
        return repository.findAll();
    }

    public Wallet findById(Long id){
        Optional<Wallet> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: " + id));
    }
}
