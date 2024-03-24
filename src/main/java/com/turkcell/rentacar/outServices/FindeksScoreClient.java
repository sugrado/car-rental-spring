package com.turkcell.rentacar.outServices;

import org.springframework.stereotype.Service;

@Service
public class FindeksScoreClient {
    public int getScoreForIndividual(String identityNumber) {
        return (int) Math.floor(Math.random() * 1901);
    }

    public int getScoreForCorporate(String taxNo) {
        return (int) Math.floor(Math.random() * 1901);
    }
}
