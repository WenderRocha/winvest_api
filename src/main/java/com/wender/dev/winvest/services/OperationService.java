package com.wender.dev.winvest.services;

import com.wender.dev.winvest.dtos.OperationDTO;
import com.wender.dev.winvest.entities.Management;
import com.wender.dev.winvest.entities.Operation;
import com.wender.dev.winvest.entities.enums.OperationResult;
import com.wender.dev.winvest.repositories.ManagementRepository;
import com.wender.dev.winvest.repositories.OperationRepository;
import com.wender.dev.winvest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    @Autowired
    private OperationRepository repository;

    @Autowired
    private ManagementService managementService;

    public Operation findById(Long id){
        Optional<Operation> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: " + id));
    }

    public List<Operation> findAll(){
        return repository.findAll();
    }

    public Operation create(OperationDTO obj){
            return fromDTO(obj);
    }

    private Operation fromDTO(OperationDTO obj){
        Operation newObj = new Operation();
        newObj.setId(obj.getId());
        newObj.setDate(LocalDateTime.now());
        newObj.setAssets(obj.getAssets());
        newObj.setComments(obj.getComments());
        newObj.setPayout(obj.getPayout());
        newObj.setValue(obj.getValue());
        newObj.setTake(OperationResult.toEnum(obj.getOperationResult()));
        newObj.setOperationResult(OperationResult.toEnum(obj.getOperationResult()));

        Management management = managementService.findById(obj.getManagement());
        newObj.setManagement(management);

        return repository.save(newObj);
    }
}
