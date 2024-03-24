package com.turkcell.rentacar.adapters;

import com.turkcell.rentacar.business.abstracts.FindeksScoreService;
import com.turkcell.rentacar.outServices.FindeksScoreClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindeksScoreAdapter implements FindeksScoreService {
    FindeksScoreClient findeksScoreClient;

    @Override
    public int getScoreForIndividual(String identityNumber) {
        return findeksScoreClient.getScoreForIndividual(identityNumber);
    }

    @Override
    public int getScoreForCorporate(String taxNo) {
        return findeksScoreClient.getScoreForCorporate(taxNo);
    }
}
