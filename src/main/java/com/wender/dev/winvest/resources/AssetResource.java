package com.wender.dev.winvest.resources;

import com.wender.dev.winvest.entities.Asset;
import com.wender.dev.winvest.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/assets")
public class AssetResource {

    @Autowired
    private AssetService service;

    @GetMapping
    public ResponseEntity<List<Asset>> findAll(){
        List<Asset> assets = service.findAll();
        return ResponseEntity.ok().body(assets);
    }

    @PostMapping
    public ResponseEntity<Asset> create(@RequestBody Asset obj){
        Asset asset = service.create(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(asset.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
