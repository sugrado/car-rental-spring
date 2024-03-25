package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CreditCardService;
import com.turkcell.rentacar.business.dtos.requests.creditCards.CreateCreditCardRequest;
import com.turkcell.rentacar.business.dtos.requests.creditCards.UpdateCreditCardRequest;
import com.turkcell.rentacar.business.dtos.responses.creditCards.CreatedCreditCardResponse;
import com.turkcell.rentacar.business.dtos.responses.creditCards.GetAllCreditCardsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.creditCards.GetCreditCardResponse;
import com.turkcell.rentacar.business.dtos.responses.creditCards.UpdatedCreditCardResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/credit-cards")
public class CreditCardsController {
    private final CreditCardService creditCardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCreditCardResponse add(@Valid @RequestBody CreateCreditCardRequest createCreditCardRequest) {
        return creditCardService.add(createCreditCardRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCreditCardResponse update(@PathVariable int id, @Valid @RequestBody UpdateCreditCardRequest creditCard) {
        return creditCardService.update(id, creditCard);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        creditCardService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetCreditCardResponse get(@PathVariable int id) {
        return creditCardService.get(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCreditCardsListItemDto> getAll() {
        return creditCardService.getAll();
    }
}
