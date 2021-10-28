package com.wender.dev.winvest.services;

import com.wender.dev.winvest.entities.User;
import com.wender.dev.winvest.entities.Wallet;
import com.wender.dev.winvest.repositories.UserRepository;
import com.wender.dev.winvest.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    public void getInstanceDB() {

        User u1 = new User(null,
                "Emanuel Tiago Martins",
                "eemanueltiagomartins@fabianocosta.com.br",
                "(48) 98324-8408",
                "764.674.843-98",
                "TxdxbMNRoj");

        User u2 = new User(null,
                "Danilo Emanuel Benício Teixeira",
                "daniloemanuel@piemme.com.br",
                "(67) 99581-7479",
                "789.173.703-73",
                "0g1xrlGfoa");

        userRepository.saveAll(Arrays.asList(u1, u2));

        Wallet w1 = new Wallet(null,"Quotex", "");
        Wallet w2 = new Wallet(null,"IQ Option", "");

        w1.deposit(new BigDecimal("60.0"));
        w2.deposit(new BigDecimal("100.0"));

        walletRepository.saveAll(Arrays.asList(w1, w2));

    }
}
