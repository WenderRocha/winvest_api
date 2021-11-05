package com.wender.dev.winvest.resources;

import com.wender.dev.winvest.dtos.UserDTO;
import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        UserDTO objDTO = new UserDTO(service.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> usersDTO = service.findAll()
                .stream()
                .map(obj -> new UserDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDTO);
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User obj){
        User newObj = service.create(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newObj
                        .getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
