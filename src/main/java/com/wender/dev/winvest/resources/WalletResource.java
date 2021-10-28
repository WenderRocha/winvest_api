package com.wender.dev.winvest.resources;

import com.wender.dev.winvest.entities.Wallet;
import com.wender.dev.winvest.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/wallets")
public class WalletResource {

    @Autowired
    private WalletService service;

    @GetMapping
    public ResponseEntity<List<Wallet>> findAll(){
        List<Wallet> wallets = service.findAll();
        return ResponseEntity.ok().body(wallets);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Wallet> findById(@PathVariable Long id){
        Wallet obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
