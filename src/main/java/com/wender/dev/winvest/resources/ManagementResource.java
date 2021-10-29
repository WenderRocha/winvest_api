package com.wender.dev.winvest.resources;

import com.wender.dev.winvest.entities.Management;
import com.wender.dev.winvest.services.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/managements")
public class ManagementResource {

    @Autowired
    private ManagementService service;

    @GetMapping
    public ResponseEntity<List<Management>> findAll(){
        List<Management> managements = service.findAll();
        return ResponseEntity.ok().body(managements);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Management> findByID(@PathVariable Long id){
        Management obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
