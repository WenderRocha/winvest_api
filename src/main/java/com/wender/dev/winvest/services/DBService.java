package com.wender.dev.winvest.services;

import com.wender.dev.winvest.entities.Management;
import com.wender.dev.winvest.entities.Operation;
import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.entities.Wallet;
import com.wender.dev.winvest.entities.enums.OperationResult;
import com.wender.dev.winvest.repositories.ManagementRepository;
import com.wender.dev.winvest.repositories.OperationRepository;
import com.wender.dev.winvest.repositories.UserRepository;
import com.wender.dev.winvest.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

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

    public void getInstanceDB() {


    /* DEV Data
        User u1 = new User(
                "Wender Rocha",
                "wender_dev@hotmail.com",
                "(19) 999810604",
                "46419220807",
                "12345678"
        );

        Wallet w1 = new Wallet(null, "Quotex", "", u1);
        w1.deposit(new BigDecimal("157.24"));

        userRepository.saveAll(Arrays.asList(u1));
        walletRepository.saveAll(Arrays.asList(w1));

        Management m1 = new Management(new BigDecimal("5"), w1);


        Operation op1 = new Operation(null,
                LocalDateTime.now(),
                "EUR/AUD",
                "Retração em M5",
                new BigDecimal("78"),
                new BigDecimal("60.0"),
                OperationResult.WIN, m1
        );

        managementRepository.saveAll(Arrays.asList(m1));
        operationRepository.saveAll(Arrays.asList(op1));


        m1.getOperations().add(op1);
        managementRepository.saveAll(Arrays.asList(m1));*/

        //Test Data
        User u1 = new User(
                "Emanuel Tiago Martins",
                "eemanueltiagomartins@fabianocosta.com.br",
                "(48) 98324-8408",
                "764.674.843-98",
                "TxdxbMNRoj");

        User u2 = new User(
                "Danilo Emanuel Benício Teixeira",
                "daniloemanuel@piemme.com.br",
                "(67) 99581-7479",
                "789.173.703-73",
                "0g1xrlGfoa");


        Wallet w1 = new Wallet(null, "Quotex",new BigDecimal("200.0"), "", u1);

        Wallet w2 = new Wallet(null, "IQ Option", new BigDecimal("100.0"), "", u1);




        userRepository.saveAll(Arrays.asList(u1, u2));
        walletRepository.saveAll(Arrays.asList(w1,w2));

        Management m1 = new Management(new BigDecimal("15"), w1);
        Management m2 = new Management(new BigDecimal("10"), w2);

        Operation op1 = new Operation(null,
                LocalDateTime.now(),
                "EUR/USD",
                "testando nova estrategia",
                new BigDecimal("87"),
                new BigDecimal("20.0"),
                OperationResult.LOSS, m1);

        Operation op2 = new Operation(null,
                LocalDateTime.now(),
                "GBP/USD",
                "testando nova estrategia",
                new BigDecimal("87"),
                new BigDecimal("20.0"),
                OperationResult.WIN,m1);

        Operation op3 = new Operation(null,
                LocalDateTime.now(),
                "EUR/USD",
                "testando nova estrategia",
                new BigDecimal("87"),
                new BigDecimal("10.0"),
                OperationResult.WIN, m1);

        Operation op4 = new Operation(null,
                LocalDateTime.now(),
                "EUR/USD",
                "testando nova estrategia",
                new BigDecimal("87"),
                new BigDecimal("10.0"),
                OperationResult.DRAW, m1);

        Operation op5 = new Operation(null,
                LocalDateTime.now(),
                "EUR/USD",
                "testando nova estrategia",
                new BigDecimal("87"),
                new BigDecimal("10.0"),
                OperationResult.WIN, m1);


        managementRepository.saveAll(Arrays.asList(m1,m2));
        operationRepository.saveAll(Arrays.asList(op1,op2,op3,op4,op5));

        m1.getOperations().add(op1);
        m1.getOperations().add(op2);

        managementRepository.saveAll(Arrays.asList(m1));

    }


}
