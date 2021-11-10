package com.wender.dev.winvest.services;

import com.wender.dev.winvest.dtos.UserDTO;
import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.repositories.UserRepository;
import com.wender.dev.winvest.services.exceptions.DataIntegratyViolationException;
import com.wender.dev.winvest.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private SecurityService securityService;

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! id: " + id));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User create(@Valid UserDTO objDTO) {
        if (findByCPF(objDTO) != null) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        if (findByEMAIL(objDTO) != null) {
            throw new DataIntegratyViolationException("E-mail já cadastrado na base de dados!");
        }

        if (findByPHONE(objDTO) != null) {
            throw new DataIntegratyViolationException("Telefone já cadastrado na base de dados!");
        }

        return repository.save(
                new User(objDTO.getName(), objDTO.getEmail(), objDTO.getPhone(), objDTO.getCpf(), securityService.encoder().encode(objDTO.getPassword()))
        );
    }

    public User update(Long id, @Valid UserDTO objDTO) {
        User oldObj = findById(id);

        if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }

        if (findByEMAIL(objDTO) != null && findByEMAIL(objDTO).getId() != id) {
            throw new DataIntegratyViolationException("E-mail já cadastrado na base de dados!");
        }

        if (findByPHONE(objDTO) != null && findByPHONE(objDTO).getId() != id) {
            throw new DataIntegratyViolationException("Telefone já cadastrado na base de dados!");
        }

        oldObj.setName(objDTO.getName());
        oldObj.setEmail(objDTO.getEmail());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setPhone(objDTO.getPhone());
        oldObj.setPassword(securityService.encoder().encode(objDTO.getPassword()));
        return repository.save(oldObj);

    }

    public void delete(Long id) {
        User obj = findById(id);

        if (obj.getWallets().size() > 0) {
            throw new DataIntegratyViolationException("Usuário possui carteira(s), não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private User findByCPF(UserDTO objDTO) {
        User user = repository.findByCPF(objDTO.getCpf());

        if (user != null) {
            return user;
        }

        return null;
    }

    private User findByEMAIL(UserDTO objDTO) {
        User user = repository.findByEMAIL(objDTO.getEmail());

        if (user != null) {
            return user;
        }

        return null;
    }

    private User findByPHONE(UserDTO objDTO) {
        User user = repository.findByPhone(objDTO.getPhone());
        if (user != null) {
            return user;
        }

        return null;
    }


}
