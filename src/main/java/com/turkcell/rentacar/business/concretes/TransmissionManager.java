package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.TransmissionService;
import com.turkcell.rentacar.business.dtos.requests.transmissions.CreateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.requests.transmissions.UpdateTransmissionRequest;
import com.turkcell.rentacar.business.dtos.responses.transmissions.CreatedTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.transmissions.GetAllTransmissionsListItemDto;
import com.turkcell.rentacar.business.dtos.responses.transmissions.GetTransmissionResponse;
import com.turkcell.rentacar.business.dtos.responses.transmissions.UpdatedTransmissionResponse;
import com.turkcell.rentacar.business.rules.TransmissionBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.turkcell.rentacar.entities.concretes.Transmission;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TransmissionManager implements TransmissionService {
    private final TransmissionRepository transmissionRepository;
    private final ModelMapperService modelMapperService;
    private final TransmissionBusinessRules transmissionBusinessRules;

    @Override
    public CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        transmissionBusinessRules.transmissionNameCanNotBeDuplicatedWhenInserted(createTransmissionRequest.getName());

        Transmission transmission = modelMapperService.forRequest().map(createTransmissionRequest, Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());

        Transmission createdTransmission = transmissionRepository.save(transmission);
        return modelMapperService.forResponse().map(createdTransmission, CreatedTransmissionResponse.class);
    }

    @Override
    public UpdatedTransmissionResponse update(int id, UpdateTransmissionRequest updateTransmissionRequest) {
        transmissionBusinessRules.transmissionIdShouldBeExist(id);
        transmissionBusinessRules.transmissionNameCanNotBeDuplicatedWhenUpdated(id, updateTransmissionRequest.getName());
        Transmission transmissionToUpdate = modelMapperService.forRequest().map(updateTransmissionRequest, Transmission.class);
        transmissionToUpdate.setId(id);
        Transmission updatedTransmission = transmissionRepository.save(transmissionToUpdate);
        return modelMapperService.forResponse().map(updatedTransmission, UpdatedTransmissionResponse.class);
    }

    @Override
    public void delete(int id) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.findById(id);
        transmissionBusinessRules.transmissionShouldBeExist(foundOptionalTransmission);
        transmissionRepository.delete(foundOptionalTransmission.get());
    }

    @Override
    public List<GetAllTransmissionsListItemDto> getAll() {
        List<Transmission> transmissions = transmissionRepository.findAll();
        return modelMapperService.forResponse().map(transmissions, new TypeToken<List<GetAllTransmissionsListItemDto>>() {
        }.getType());
    }

    @Override
    public GetTransmissionResponse get(int id) {
        Optional<Transmission> foundOptionalTransmission = transmissionRepository.findById(id);
        transmissionBusinessRules.transmissionShouldBeExist(foundOptionalTransmission);
        return modelMapperService.forResponse().map(foundOptionalTransmission.get(), GetTransmissionResponse.class);
    }
}
