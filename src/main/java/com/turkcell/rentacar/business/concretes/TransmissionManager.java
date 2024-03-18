package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.business.dtos.requests.CreateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.UpdateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.*;
import com.turkcell.rentacar.business.dtos.responses.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.UpdatedTransmissionResponse;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.turkcell.rentacar.entities.concretes.Transmission;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TransmissionManager implements TransmissionService {
    TransmissionRepository transmissionRepository; // IoC
    private static final String transmissionNotFoundMessage = "Transmission not found";
    private static final String transmissionAlreadyExistsMessage = "Transmission already exists";
    ModelMapperService modelMapperService;

    @Override
    public CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        transmissionNameCanNotBeDuplicatedWhenInserted(createTransmissionRequest.getName());

        Transmission transmission = modelMapperService.forRequest().map(createTransmissionRequest, Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());

        Transmission createdTransmission = transmissionRepository.save(transmission);
        return modelMapperService.forResponse().map(createdTransmission, CreatedTransmissionResponse.class);
    }

    @Override
    public UpdatedTransmissionResponse update(int id, UpdateTransmissionRequest updateTransmissionRequest) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.findById(id);
        transmissionShouldBeExist(foundOptionalTransmission);
        transmissionNameCanNotBeDuplicatedWhenUpdated(id, updateTransmissionRequest.getName());

        Transmission transmissionToUpdate = foundOptionalTransmission.get();
        modelMapperService.forRequest().map(updateTransmissionRequest, transmissionToUpdate);
        transmissionToUpdate.setUpdatedDate(LocalDateTime.now());

        Transmission updatedTransmission = transmissionRepository.save(transmissionToUpdate);
        return modelMapperService.forResponse().map(updatedTransmission, UpdatedTransmissionResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.findById(id);
        transmissionShouldBeExist(foundOptionalTransmission);
        transmissionRepository.delete(foundOptionalTransmission.get());
    }

    @Override
    public List<Transmission> getAll() {
        return transmissionRepository.findAll();
    }

    @Override
    public GetTransmissionResponse get(int id) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.findById(id);
        transmissionShouldBeExist(foundOptionalTransmission);
        return modelMapperService.forResponse().map(foundOptionalTransmission.get(), GetTransmissionResponse.class);
    }

    // temp business rules
    // TODO: TransmissionBusinessRules
    private void transmissionShouldBeExist(Optional<Transmission> transmission) {
        if (transmission.isEmpty()) {
            throw new RuntimeException(transmissionNotFoundMessage);
        }
    }

    private void transmissionNameCanNotBeDuplicatedWhenInserted(String name) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.getByNameIgnoreCase(name.trim());
        if (foundOptionalTransmission.isPresent()) {
            throw new RuntimeException(transmissionAlreadyExistsMessage);
        }
    }

    private void transmissionNameCanNotBeDuplicatedWhenUpdated(int id, String name) {
        boolean exists = transmissionRepository.existsByNameIgnoreCaseAndIdIsNot(name.trim(), id);
        if (exists) {
            throw new RuntimeException(transmissionAlreadyExistsMessage);
        }
    }
}
