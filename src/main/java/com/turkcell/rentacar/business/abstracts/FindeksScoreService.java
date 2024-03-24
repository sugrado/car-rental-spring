package com.turkcell.rentacar.business.abstracts;

public interface FindeksScoreService {
    int getScoreForIndividual(String identityNumber);

    int getScoreForCorporate(String taxNo);
}
