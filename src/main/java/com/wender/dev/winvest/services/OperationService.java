package com.wender.dev.winvest.services;

import com.wender.dev.winvest.dtos.OperationDTO;
import com.wender.dev.winvest.entities.Management;
import com.wender.dev.winvest.entities.Operation;
import com.wender.dev.winvest.entities.enums.OperationResult;
import com.wender.dev.winvest.repositories.OperationRepository;
import com.wender.dev.winvest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    @Autowired
    private ManagementService managementService;

    @Autowired
    private OperationRepository repository;

    @Autowired
    BookService bookService;

    public Operation findById(Long id) {
        Optional<Operation> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: " + id));
    }

    public List<Operation> findAll() {
        return repository.findAll();
    }

    public Operation create(@Valid OperationDTO obj) {
        return fromDTO(obj);
    }

    public Operation update(Long id, @Valid OperationDTO objDTO) {

        Operation oldObj = findById(id);
        oldObj.setAssets(objDTO.getAssets());
        oldObj.setComments(objDTO.getComments());
        oldObj.setPayout(objDTO.getPayout());
        oldObj.setValue(objDTO.getValue());
        oldObj.setOperationResult(OperationResult.toEnum(objDTO.getOperationResult()));
        return repository.save(oldObj);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    private Operation fromDTO(OperationDTO obj) {
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

        Operation save = repository.save(newObj);

        createOrUpdateBook(management);

        return save;
    }

    public void createOrUpdateBook(Management management) {

        if (bookService.findByDate(LocalDate.now()) != null) {
            bookService.update(management);
        } else {
            bookService.create(management);
        }
    }

}
