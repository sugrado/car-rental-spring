package com.turkcell.rentacar.outServices;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosClient {
    public boolean createPayment(String cardNumber, String cardHolderName, String cvv, String expireDate, double amount) {
        Random random = new Random();
        return random.nextBoolean();
    }
}
