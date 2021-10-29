package com.wender.dev.winvest.services;

import com.wender.dev.winvest.entities.Management;
import com.wender.dev.winvest.repositories.ManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagementService {

    @Autowired
    private ManagementRepository repository;

    public List<Management> findAll(){
        return repository.findAll();
    }

    public Management findById(Long id){
        Optional<Management> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
