package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.CreditCardMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.CreditCardRepository;
import com.turkcell.rentacar.entities.concretes.CreditCard;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreditCardBusinessRules {
    private final CreditCardRepository creditCardRepository;

    public void creditCardShouldBeExist(Optional<CreditCard> creditCard) {
        if (creditCard.isEmpty()) {
            throw new BusinessException(CreditCardMessages.creditCardNotFound);
        }
    }

    public void creditCardIdShouldBeExist(int creditCardId) {
        Optional<CreditCard> creditCard = creditCardRepository.findById(creditCardId);
        if (creditCard.isEmpty()) {
            throw new BusinessException(CreditCardMessages.creditCardNotFound);
        }
    }

    public void creditCardNameCanNotBeDuplicatedWhenInserted(String name) {
        Optional<CreditCard> foundOptionalCreditCard = creditCardRepository.findByNameIgnoreCase(name.trim());
        if (foundOptionalCreditCard.isPresent()) {
            throw new BusinessException(CreditCardMessages.creditCardAlreadyExists);
        }
    }

    public void creditCardNameCanNotBeDuplicatedWhenUpdated(int id, String name) {
        boolean exists = creditCardRepository.existsByNameIgnoreCaseAndIdIsNot(name.trim(), id);
        if (exists) {
            throw new BusinessException(CreditCardMessages.creditCardAlreadyExists);
        }
    }
}
