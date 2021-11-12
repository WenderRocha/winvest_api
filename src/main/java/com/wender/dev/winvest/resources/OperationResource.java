package com.wender.dev.winvest.resources;

import com.wender.dev.winvest.dtos.OperationDTO;
import com.wender.dev.winvest.entities.Operation;
import com.wender.dev.winvest.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Operation> create(@Valid  @RequestBody OperationDTO objDTO){
        objDTO = new OperationDTO(service.create(objDTO));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(objDTO.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OperationDTO> update(@PathVariable Long id, @Valid @RequestBody OperationDTO objDTO){
        OperationDTO newObj = new OperationDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
