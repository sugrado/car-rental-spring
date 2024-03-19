package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TransmissionBusinessRules {
    private final TransmissionRepository transmissionRepository;
    private static final String transmissionNotFoundMessage = "Transmission not found";
    private static final String transmissionAlreadyExistsMessage = "Transmission already exists";

    public void transmissionShouldBeExist(Optional<Transmission> transmission) {
        if (transmission.isEmpty()) {
            throw new RuntimeException(transmissionNotFoundMessage);
        }
    }

    public void transmissionNameCanNotBeDuplicatedWhenInserted(String name) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.getByNameIgnoreCase(name.trim());
        if (foundOptionalTransmission.isPresent()) {
            throw new RuntimeException(transmissionAlreadyExistsMessage);
        }
    }

    public void transmissionNameCanNotBeDuplicatedWhenUpdated(int id, String name) {
        boolean exists = transmissionRepository.existsByNameIgnoreCaseAndIdIsNot(name.trim(), id);
        if (exists) {
            throw new RuntimeException(transmissionAlreadyExistsMessage);
        }
    }
}