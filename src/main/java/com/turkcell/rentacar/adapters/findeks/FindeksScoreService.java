package com.turkcell.rentacar.adapters.findeks;

public interface FindeksScoreService {
    int getScoreForIndividual(String identityNumber);

    int getScoreForCorporate(String taxNo);
}
