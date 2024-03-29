package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.ProductService;
import com.turkcell.rentacar.business.abstracts.RentalProductService;
import com.turkcell.rentacar.business.dtos.requests.payments.CreatePaymentRequest;
import com.turkcell.rentacar.business.dtos.requests.rentalProducts.CreateRentalProductListItemDto;
import com.turkcell.rentacar.business.dtos.requests.rentalProducts.CreateRentalProductRequest;
import com.turkcell.rentacar.business.dtos.responses.payments.CreatedPaymentResponse;
import com.turkcell.rentacar.business.dtos.responses.rentalProducts.CreatedRentalProductListItemDto;
import com.turkcell.rentacar.business.dtos.responses.rentalProducts.GetAllRentalProductsListItemDto;
import com.turkcell.rentacar.business.rules.PaymentBusinessRules;
import com.turkcell.rentacar.business.rules.ProductBusinessRules;
import com.turkcell.rentacar.business.rules.RentalBusinessRules;
import com.turkcell.rentacar.business.rules.RentalProductBusinessRules;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.RentalProductRepository;
import com.turkcell.rentacar.entities.concretes.Product;
import com.turkcell.rentacar.entities.concretes.Rental;
import com.turkcell.rentacar.entities.concretes.RentalProduct;
import lombok.AllArgsConstructor;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RentalProductManager implements RentalProductService {
    private final RentalProductRepository rentalProductRepository;
    private final ProductBusinessRules productBusinessRules;
    private final RentalBusinessRules rentalBusinessRules;
    private final PaymentBusinessRules paymentBusinessRules;
    private final RentalProductBusinessRules rentalProductBusinessRules;
    private final ModelMapperService modelMapperService;
    private final PaymentService paymentService;
    private final ProductService productService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CreatedRentalProductListItemDto> add(int rentalId, CreateRentalProductRequest createRentalProductRequest) {
        rentalBusinessRules.rentalIdShouldBeExist(rentalId);
        rentalProductBusinessRules.rentalEndDateShouldNotBePassed(rentalId);
        productBusinessRules.allProductsShouldBeExists(createRentalProductRequest.getRentalProductList());

        List<RentalProduct> createdRentalProducts = addProductsToRental(rentalId, createRentalProductRequest.getRentalProductList());
        double totalPrice = productService.calculateTotalPrice(createdRentalProducts);
        CreatePaymentRequest createPaymentRequest = CreatePaymentRequest.builder()
                .rentalId(rentalId)
                .amount(totalPrice)
                .creditCard(createRentalProductRequest.getCreditCard())
                .build();

        CreatedPaymentResponse paymentResponse = paymentService.add(createPaymentRequest);
        paymentBusinessRules.paymentShouldBeSuccess(paymentResponse.getState());
        return modelMapperService.forResponse().map(createdRentalProducts, new TypeToken<List<CreatedRentalProductListItemDto>>() {
        }.getType());
    }

    @Override
    public List<GetAllRentalProductsListItemDto> getAllByRental(int rentalId) {
        List<RentalProduct> rentalProducts = rentalProductRepository.findByRentalId(rentalId);
        return modelMapperService.forResponse().map(rentalProducts, new TypeToken<List<GetAllRentalProductsListItemDto>>() {
        }.getType());
    }

    @Override
    public List<RentalProduct> addProductsToRental(int rentalId, List<CreateRentalProductListItemDto> createRentalProducts) {
        Rental rental = new Rental();
        rental.setId(rentalId);
        List<RentalProduct> rentalProducts = createRentalProducts.stream().map(p -> {
            Product product = new Product();
            product.setId(p.getProductId());
            return new RentalProduct(p.getQuantity(), rental, product);
        }).toList();

        return rentalProductRepository.saveAll(rentalProducts);
    }
}
