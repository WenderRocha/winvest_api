package com.wender.dev.winvest.services;

import com.wender.dev.winvest.dtos.WalletDTO;
import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.entities.Wallet;
import com.wender.dev.winvest.repositories.WalletRepository;
import com.wender.dev.winvest.services.exceptions.DataIntegratyViolationException;
import com.wender.dev.winvest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ManagementService managementService;

    public Wallet findById(Long id){
        Optional<Wallet> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: " + id));
    }

    public List<Wallet> findAll(){
        return repository.findAll();
    }

    public Wallet create(@Valid WalletDTO obj){
        return fromDTO(obj);
    }

    private Wallet fromDTO(WalletDTO obj){
        Wallet newObj = new Wallet();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        newObj.setBalance(obj.getBalance());
        newObj.setImgUrl(obj.getImgUrl());

        User user = userService.findById(obj.getUser());
        newObj.setUser(user);

        return repository.save(newObj);
    }

    public Wallet update(Long id, @Valid WalletDTO objDTO) {

        Wallet oldObj = findById(id);
        oldObj.setName(objDTO.getName());
        oldObj.setBalance(objDTO.getBalance());
        oldObj.setImgUrl(objDTO.getImgUrl());
        return repository.save(oldObj);
    }

    public void delete(Long id) {

       Wallet wallet = findById(id);

        if(managementService.findByWallet(wallet)){
            throw new DataIntegratyViolationException("Carteira está sendo gerênciada, não pode ser deletada.");
        }

        //deleta a carteira.
        repository.deleteById(id);
    }
}
