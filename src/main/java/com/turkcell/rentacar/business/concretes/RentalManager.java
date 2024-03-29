package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.*;
import com.turkcell.rentacar.business.dtos.requests.payments.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.payments.CreatePendingPaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.rentals.CreateRentalRequest;
import com.turkcell.rentacar.business.dtos.requests.rentals.ReturnCarRequest;
import com.turkcell.rentacar.business.dtos.requests.rentals.UpdateRentalRequest;
import com.turkcell.rentacar.business.dtos.responses.payments.CreatedPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.rentals.CreatedRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.rentals.GetAllRentalsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.rentals.GetRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.rentals.UpdatedRentalResponse;
import com.turkcell.rentacar.business.rules.*;
import com.turkcell.rentacar.core.utilities.helpers.DateHelper;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.Rental;
import com.turkcell.rentacar.entities.concretes.RentalProduct;
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
    private final CustomerBusinessRules customerBusinessRules;
    private final ProductBusinessRules productBusinessRules;
    private final CarService carService;
    private final PaymentService paymentService;
    private final ProductService productService;
    private final RentalProductService rentalProductService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CreatedRentalResponse add(CreateRentalRequest createRentalRequest) {
        customerBusinessRules.customerIdShouldBeExist(createRentalRequest.getCustomerId());
        carBusinessRules.carIdShouldBeExist(createRentalRequest.getCarId());
        carBusinessRules.carShouldNotBeInMaintenance(createRentalRequest.getCarId());
        carBusinessRules.carShouldNotBeRented(createRentalRequest.getCarId());
        rentalBusinessRules.customerFindeksScoreShouldBeEnough(createRentalRequest.getCustomerId(), createRentalRequest.getCarId());
        productBusinessRules.allProductsShouldBeExists(createRentalRequest.getProducts());

        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        Rental createdRental = rentalRepository.save(rental);

        List<RentalProduct> createdRentalProducts = rentalProductService.addProductsToRental(rental.getId(), createRentalRequest.getProducts());
        double totalPrice = 0;
        totalPrice += carService.calculatePriceByDays(createRentalRequest.getCarId(), DateHelper.totalDaysBetween(rental.getStartDate(), rental.getEndDate()));
        totalPrice += productService.calculateTotalPrice(createdRentalProducts);

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
    }

    private void createLateReturnPaymentIfLate(Rental rental) {
        if (rental.getReturnDate().isBefore(rental.getEndDate())) {
            return;
        }
        double price = carService.calculateLateFeeByDays(rental.getCar().getId(),
                DateHelper.totalDaysBetween(rental.getEndDate(), rental.getReturnDate()));
        CreatePendingPaymentRequest createPendingPaymentRequest = new CreatePendingPaymentRequest(rental.getId(), price);
        paymentService.addPendingPayment(createPendingPaymentRequest);
    }
}
