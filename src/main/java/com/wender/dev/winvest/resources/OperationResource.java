package com.wender.dev.winvest.resources;

import com.wender.dev.winvest.entities.Operation;
import com.wender.dev.winvest.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/operations")
public class OperationResource {

    @Autowired
    private OperationService service;

    @GetMapping
    public ResponseEntity<List<Operation>> findAll(){
        List<Operation> operations = service.findAll();
        return ResponseEntity.ok().body(operations);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Operation> findById(@PathVariable Long id){
        Operation obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
