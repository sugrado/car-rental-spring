package com.turkcell.rentacar.business.rules;

import com.turkcell.rentacar.business.constants.messages.ModelMessages;
import com.turkcell.rentacar.core.utilities.exceptions.types.BusinessException;
import com.turkcell.rentacar.dataAccess.abstracts.ModelRepository;
import com.turkcell.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ModelBusinessRules {
    private final ModelRepository modelRepository;

    public void modelShouldBeExist(Optional<Model> model) {
        if (model.isEmpty()) {
            throw new BusinessException(ModelMessages.MODEL_NOT_FOUND);
        }
    }

    public void modelIdShouldBeExist(int modelId) {
        Optional<Model> model = modelRepository.findById(modelId);
        if (model.isEmpty()) {
            throw new BusinessException(ModelMessages.MODEL_NOT_FOUND);
        }
    }
}
