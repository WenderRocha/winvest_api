package com.wender.dev.winvest.services;

import com.wender.dev.winvest.dtos.ManagementDTO;
import com.wender.dev.winvest.dtos.WalletDTO;
import com.wender.dev.winvest.entities.Management;
import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.entities.Wallet;
import com.wender.dev.winvest.repositories.ManagementRepository;
import com.wender.dev.winvest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ManagementService {

    @Autowired
    private ManagementRepository repository;

    @Autowired
    private WalletService walletService;

    public Management findById(Long id){
        Optional<Management> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: " + id));
    }

    public List<Management> findAll(){
        return repository.findAll();
    }

    public Management create(@Valid ManagementDTO obj){
        return fromDTO(obj);
    }

    private Management fromDTO(ManagementDTO obj){
        Management newObj = new Management();
        newObj.setId(obj.getId());
        newObj.setTarget(obj.getTarget());
        newObj.setStop(obj.getStop());

        Wallet wallet = walletService.findById(obj.getWallet());
        newObj.setWallet(wallet);

        return repository.save(newObj);
    }

    public Boolean findByWallet(Wallet obj){
        for (Management management : findAll()){

            if(obj.getId().equals(management.getWallet().getId())){
                return true;
            }

        }

        return false;

    }


}
