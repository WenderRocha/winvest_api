package com.wender.dev.winvest.services;

import com.wender.dev.winvest.entities.Asset;
import com.wender.dev.winvest.repositories.AssetRepository;
import com.wender.dev.winvest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    private AssetRepository repository;

    public Asset findById(Long id){
        Optional<Asset> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: " + id));
    }

    public List<Asset> findAll(){
        return repository.findAll();
    }

    public Asset create(Asset obj){
        Asset asset = new Asset();
        asset.setName(obj.getName());
        asset.setImg1(obj.getImg1());
        asset.setImg2(obj.getImg2());

        return repository.save(asset);
    }
}
