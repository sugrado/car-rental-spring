package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.requests.payments.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.payments.CreateWithoutPaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.rentals.CreateRentalRequest;
import com.turkcell.rentacar.business.dtos.requests.rentals.ReturnCarRequest;
import com.turkcell.rentacar.business.dtos.requests.rentals.UpdateRentalRequest;
import com.turkcell.rentacar.business.dtos.responses.payments.CreatedPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.rentals.*;
import com.turkcell.rentacar.business.rules.CarBusinessRules;
import com.turkcell.rentacar.business.rules.PaymentBusinessRules;
import com.turkcell.rentacar.business.rules.RentalBusinessRules;
import com.turkcell.rentacar.core.utilities.helpers.DateHelper;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.Rental;
import com.turkcell.rentacar.entities.enums.CarState;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final RentalBusinessRules rentalBusinessRules;
    private final CarBusinessRules carBusinessRules;
    private final PaymentBusinessRules paymentBusinessRules;
    private final CarService carService;
    private final PaymentService paymentService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CreatedRentalResponse add(CreateRentalRequest createRentalRequest) {
        carBusinessRules.carIdShouldBeExist(createRentalRequest.getCarId());
        carBusinessRules.carShouldNotBeInMaintenance(createRentalRequest.getCarId());
        carBusinessRules.carShouldNotBeRented(createRentalRequest.getCarId());
        rentalBusinessRules.customerFindeksScoreShouldBeEnough(createRentalRequest.getCustomerId(), createRentalRequest.getCarId());

        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        Rental createdRental = rentalRepository.save(rental);

        double totalPrice = 0;
        totalPrice += carService.calculatePriceByDays(createRentalRequest.getCarId(), DateHelper.totalDaysBetween(rental.getStartDate(), rental.getEndDate()));
        totalPrice += 2; // TODO: Ek servis ücretleri

        CreatePaymentRequest createPaymentRequest = CreatePaymentRequest.builder()
                .rentalId(createdRental.getId())
                .amount(totalPrice)
                .creditCard(createRentalRequest.getCreditCard())
                .build();
        CreatedPaymentResponse createdPaymentResponse = paymentService.add(createPaymentRequest);
        paymentBusinessRules.paymentShouldBeSuccess(createdPaymentResponse.getState());

        carService.updateState(createRentalRequest.getCarId(), CarState.RENTED);
        return modelMapperService.forResponse().map(createdRental, CreatedRentalResponse.class);
    }

    @Override
    public UpdatedRentalResponse update(int id, UpdateRentalRequest updateRentalRequest) {
        rentalBusinessRules.rentalIdShouldBeExist(id);
        Rental rentalToUpdate = modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        rentalToUpdate.setId(id);
        Rental updatedRental = rentalRepository.save(rentalToUpdate);
        return modelMapperService.forResponse().map(updatedRental, UpdatedRentalResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<Rental> foundOptionalRental = rentalRepository.findById(id);
        rentalBusinessRules.rentalShouldBeExist(foundOptionalRental);
        rentalRepository.delete(foundOptionalRental.get());
    }

    @Override
    public List<GetAllRentalsListItemDto> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        return modelMapperService.forResponse().map(rentals, new TypeToken<List<GetAllRentalsListItemDto>>() {
        }.getType());
    }

    @Override
    public GetRentalResponse get(int id) {
        Optional<Rental> foundOptionalRental = rentalRepository.findById(id);
        rentalBusinessRules.rentalShouldBeExist(foundOptionalRental);
        return modelMapperService.forResponse().map(foundOptionalRental.get(), GetRentalResponse.class);
    }

    @Override
    public void returnCar(ReturnCarRequest returnCarRequest) {
        Optional<Rental> foundOptionalRental = rentalRepository.findById(returnCarRequest.getRentalId());
        rentalBusinessRules.rentalShouldBeExist(foundOptionalRental);
        Rental rental = foundOptionalRental.get();
        rentalBusinessRules.rentalShouldNotBeReturned(rental);

        rental.setReturnDate(returnCarRequest.getReturnDate());
        createLateReturnPaymentIfLate(rental);
        rentalRepository.save(rental);

        carService.updateState(rental.getCar().getId(), CarState.AVAILABLE);
        return modelMapperService.forResponse().map(rental, ReturnedCarResponse.class);
    }

    private void createLateReturnPaymentIfLate(Rental rental) {
        if (rental.getReturnDate().isBefore(rental.getEndDate())) {
            return;
        }
        double price = carService.calculateLateFeeByDays(rental.getCar().getId(),
                DateHelper.totalDaysBetween(rental.getEndDate(), rental.getReturnDate()));
        CreateWithoutPaymentRequest createWithoutPaymentRequest = new CreateWithoutPaymentRequest(rental.getId(), price);
        paymentService.addWithoutPayment(createWithoutPaymentRequest);
    }
}
