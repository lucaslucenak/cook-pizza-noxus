package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.AddressDto;
import com.teclinecg.noxus.dtos.AddressPostDto;
import com.teclinecg.noxus.dtos.CustomerAccountDto;
import com.teclinecg.noxus.dtos.CustomerAccountPostDto;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.AddressModel;
import com.teclinecg.noxus.models.CustomerAccountModel;
import com.teclinecg.noxus.repositories.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerAccountService customerAccountService;

    @Transactional
    public AddressDto findAddressById(Long id) {
        Optional<AddressModel> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()) {
            return new AddressDto(addressOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Address. Not found with id: " + id);
        }
    }

    @Transactional
    public Page<AddressDto> findAllAddressesPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater or equal than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<AddressModel> pagedAddresses = addressRepository.findAll(pageable);

        return pagedAddresses.map(AddressDto::new);
    }

    @Transactional
    public AddressDto saveAddress(AddressPostDto addressPostDto) {
        CustomerAccountModel customerAccountModel = new CustomerAccountModel(customerAccountService.findCustomerAccountById(addressPostDto.getCustomerAccount()));
        AddressModel addressModel = new AddressModel(addressPostDto);
        addressModel.setCustomerAccount(customerAccountModel);

        return new AddressDto(addressRepository.save(addressModel));
    }

    @Transactional
    public AddressPostDto updateAddress(Long id, AddressPostDto addressDto) {
        Optional<AddressModel> existentAddressModelOptional = addressRepository.findById(id);

        if (existentAddressModelOptional.isPresent()) {
            AddressModel updatedAddressModel = new AddressModel(addressDto);
            BeanUtils.copyProperties(existentAddressModelOptional, updatedAddressModel);
            return new AddressPostDto(addressRepository.save(updatedAddressModel));
        } else {
            throw new ResourceNotFoundException("Resource: Address. Not found with id: " + id);
        }
    }

    @Transactional
    public void deleteAddressById(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Resource: Address. Not found with id: " + id);
        }
    }
}
