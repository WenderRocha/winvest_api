package com.wender.dev.winvest.services;

import com.wender.dev.winvest.entities.Asset;
import com.wender.dev.winvest.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AssetRepository assetRepository;

    public void getInstanceDB() {
        Asset asset1 = new Asset(null,
                "EUR/USD",
                "eur.png",
                "usd.png");

        Asset asset2 = new Asset(null,
                "NZD/USD",
                "nzd.png",
                "usd.png");

        Asset asset3 = new Asset(null,
                "NZD/JPY",
                "nzd.png",
                "jpy.png");

        Asset asset4 = new Asset(null,
                "CAD/JPY",
                "cad.png",
                "jpy.png");

        Asset asset5 = new Asset(null,
                "AUD/CAD",
                "aud.png",
                "cad.png");

        Asset asset6 = new Asset(null,
                "AUD/JPY",
                "aud.png",
                "jpy.png");

        Asset asset7 = new Asset(null,
                "GBP/CAD",
                "gbp.png",
                "cad.png");

        Asset asset8 = new Asset(null,
                "GBP/AUD",
                "gbp.png",
                "aud.png");

        Asset asset9 = new Asset(null,
                "USD/CAD",
                "usd.png",
                "cad.png");

        Asset asset10 = new Asset(null,
                "EUR/GBP",
                "eur.png",
                "gbp.png");

        Asset asset11 = new Asset(null,
                "GBP/CHF",
                "gbp.png",
                "chf.png");

        Asset asset12 = new Asset(null,
                "EUR/SGD",
                "eur.png",
                "sgd.png");

        Asset asset13 = new Asset(null,
                "AUD/USD",
                "aud.png",
                "usd.png");


        assetRepository.saveAll(Arrays.asList(asset1,asset2,asset3,asset4,asset5,asset6,asset7,asset8,asset9,asset10,asset11,asset12,asset13));
    }


}
