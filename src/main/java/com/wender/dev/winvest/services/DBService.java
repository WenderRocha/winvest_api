package com.wender.dev.winvest.services;

import com.wender.dev.winvest.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private ManagementRepository managementRepository;

    @Autowired
    private OperationRepository operationRepository;

    private BookRepository bookRepository;

    public void getInstanceDB() {


    }


}
