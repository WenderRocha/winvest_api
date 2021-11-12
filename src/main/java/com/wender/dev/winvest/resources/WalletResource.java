package com.wender.dev.winvest.resources;

import com.wender.dev.winvest.dtos.WalletDTO;
import com.wender.dev.winvest.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/wallets")
public class WalletResource {

    @Autowired
    private WalletService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<WalletDTO> findById(@PathVariable Long id){
        WalletDTO obj = new WalletDTO(service.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<WalletDTO>> findAll(){
       List<WalletDTO> list = service.findAll()
               .stream()
               .map(WalletDTO::new)
               .collect(Collectors.toList());
       return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<WalletDTO> create(@Valid @RequestBody WalletDTO obj){
        obj = new WalletDTO(service.create(obj));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<WalletDTO> update(@PathVariable Long id, @Valid @RequestBody WalletDTO objDTO){
        WalletDTO newObj = new WalletDTO(service.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
