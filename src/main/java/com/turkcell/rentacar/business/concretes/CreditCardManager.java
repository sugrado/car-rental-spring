package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CreditCardService;
import com.turkcell.rentacar.business.dtos.requests.creditCards.CreateCreditCardRequest;
import com.turkcell.rentacar.business.dtos.requests.creditCards.UpdateCreditCardRequest;
import com.turkcell.rentacar.business.dtos.responses.creditCards.CreatedCreditCardResponse;
import com.turkcell.rentacar.business.dtos.responses.creditCards.GetAllCreditCardsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.creditCards.GetCreditCardResponse;
import com.turkcell.rentacar.business.dtos.responses.creditCards.UpdatedCreditCardResponse;
import com.turkcell.rentacar.business.rules.CreditCardBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.CreditCardRepository;
import com.turkcell.rentacar.entities.concretes.CreditCard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreditCardManager implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final CreditCardBusinessRules creditCardBusinessRules;
    private final ModelMapperService modelMapperService;

    @Override
    public CreatedCreditCardResponse add(CreateCreditCardRequest createCreditCardRequest) {
        creditCardBusinessRules.creditCardNameCanNotBeDuplicatedWhenInserted(createCreditCardRequest.getName());

        CreditCard creditCard = modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
        creditCard.setId(0);
        creditCard.setCreatedDate(LocalDateTime.now());

        CreditCard createdCreditCard = creditCardRepository.save(creditCard);
        return modelMapperService.forResponse().map(createdCreditCard, CreatedCreditCardResponse.class);
    }

    @Override
    public UpdatedCreditCardResponse update(int id, UpdateCreditCardRequest updateCreditCardRequest) {
        creditCardBusinessRules.creditCardIdShouldBeExist(id);
        creditCardBusinessRules.creditCardNameCanNotBeDuplicatedWhenUpdated(id, updateCreditCardRequest.getName());
        CreditCard creditCardToUpdate = modelMapperService.forRequest().map(updateCreditCardRequest, CreditCard.class);
        creditCardToUpdate.setId(id);
        CreditCard updatedCreditCard = creditCardRepository.save(creditCardToUpdate);
        return modelMapperService.forResponse().map(updatedCreditCard, UpdatedCreditCardResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<CreditCard> foundOptionalCreditCard = creditCardRepository.findById(id);
        creditCardBusinessRules.creditCardShouldBeExist(foundOptionalCreditCard);
        creditCardRepository.delete(foundOptionalCreditCard.get());
    }

    @Override
    public GetCreditCardResponse get(int id) {
        Optional<CreditCard> foundOptionalCreditCard = creditCardRepository.findById(id);
        creditCardBusinessRules.creditCardShouldBeExist(foundOptionalCreditCard);
        return modelMapperService.forResponse().map(foundOptionalCreditCard.get(), GetCreditCardResponse.class);
    }

    @Override
    public CreditCard getRecord(int id) {
        Optional<CreditCard> foundOptionalCreditCard = creditCardRepository.findById(id);
        creditCardBusinessRules.creditCardShouldBeExist(foundOptionalCreditCard);
        return foundOptionalCreditCard.get();
    }

    @Override
    public List<GetAllCreditCardsListItemDto> getAll() {
        List<CreditCard> creditCards = creditCardRepository.findAll();
        return creditCards.stream()
                .map(creditCard -> this.modelMapperService.forResponse()
                        .map(creditCard, GetAllCreditCardsListItemDto.class))
                .toList();
    }
}
