package com.wender.dev.winvest.resources;

import com.wender.dev.winvest.dtos.ManagementDTO;
import com.wender.dev.winvest.entities.Management;
import com.wender.dev.winvest.entities.Wallet;
import com.wender.dev.winvest.services.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin("*")
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

    @PostMapping
    public ResponseEntity<Wallet> create(@Valid @RequestBody ManagementDTO obj){
        obj = new ManagementDTO(service.create(obj));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ManagementDTO> update(@PathVariable Long id, @Valid @RequestBody ManagementDTO objDTO){
        ManagementDTO newObj = new ManagementDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
