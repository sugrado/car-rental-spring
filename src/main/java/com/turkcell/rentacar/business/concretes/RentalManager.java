package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.requests.rentals.CreateRentalRequest;
import com.turkcell.rentacar.business.dtos.requests.rentals.UpdateRentalRequest;
import com.turkcell.rentacar.business.dtos.responses.rentals.CreatedRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.rentals.GetAllRentalsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.rentals.GetRentalResponse;
import com.turkcell.rentacar.business.dtos.responses.rentals.UpdatedRentalResponse;
import com.turkcell.rentacar.business.rules.CarBusinessRules;
import com.turkcell.rentacar.business.rules.RentalBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.RentalRepository;
import com.turkcell.rentacar.entities.concretes.Rental;
import com.turkcell.rentacar.entities.enums.CarState;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final RentalBusinessRules rentalBusinessRules;
    private final CarBusinessRules carBusinessRules;
    private final CarService carService;
    private final InvoiceService invoiceService;

    @Override
    @Transactional
    public CreatedRentalResponse add(CreateRentalRequest createRentalRequest) {
        carBusinessRules.carIdShouldBeExist(createRentalRequest.getCarId());
        carBusinessRules.carShouldNotBeInMaintenance(createRentalRequest.getCarId());
        carBusinessRules.carShouldNotBeRented(createRentalRequest.getCarId());
        rentalBusinessRules.customerFindeksScoreShouldBeEnough(createRentalRequest.getCustomerId(), createRentalRequest.getCarId());
        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
         // TODO: manager'lardaki updatedDate ve createdDate'lerin hepsi kaldırılacak.

        carService.updateState(createRentalRequest.getCarId(), CarState.RENTED);
        // TODO: rental'e sonradan ek hizmet satın alırken rental süresinin geçmemiş olması lazım
        // TODO: rental ve maintenance'a return methodu yazılacak
        Rental createdRental = rentalRepository.save(rental);
        invoiceService.add(createdRental, createRentalRequest.getCreditCardId());
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
}
