package com.wender.dev.winvest.services;

import com.wender.dev.winvest.entities.Operation;
import com.wender.dev.winvest.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    @Autowired
    private OperationRepository repository;

    public List<Operation> findAll(){
        return repository.findAll();
    }

    public Operation findById(Long id){
        Optional<Operation> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
