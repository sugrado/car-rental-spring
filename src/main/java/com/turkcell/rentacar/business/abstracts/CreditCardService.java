package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.requests.creditCards.CreateCreditCardRequest;
import com.turkcell.rentacar.business.dtos.requests.creditCards.UpdateCreditCardRequest;
import com.turkcell.rentacar.business.dtos.responses.creditCards.CreatedCreditCardResponse;
import com.turkcell.rentacar.business.dtos.responses.creditCards.GetAllCreditCardsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.creditCards.GetCreditCardResponse;
import com.turkcell.rentacar.business.dtos.responses.creditCards.UpdatedCreditCardResponse;
import com.turkcell.rentacar.entities.concretes.CreditCard;

import java.util.List;

public interface CreditCardService {
    CreatedCreditCardResponse add(CreateCreditCardRequest createCreditCardRequest);

    UpdatedCreditCardResponse update(int id, UpdateCreditCardRequest updateCreditCardRequest);

    void delete(int id);

    GetCreditCardResponse get(int id);

    CreditCard getRecord(int id);

    List<GetAllCreditCardsListItemDto> getAll();
}
