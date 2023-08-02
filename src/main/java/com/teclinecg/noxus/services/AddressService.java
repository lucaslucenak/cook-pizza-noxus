package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.AddressDtoDefault;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.AddressModel;
import com.teclinecg.noxus.repositories.AddressRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressDtoDefault findAddressById(Long id) {
        Optional<AddressModel> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()) {
            return new AddressDtoDefault(addressOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Address. Not found with id: " + id);
        }
    }

    public Page<AddressDtoDefault> findAllAddressesPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<AddressModel> pagedAddresses = addressRepository.findAll(pageable);

        return pagedAddresses.map(AddressDtoDefault::new);
    }

    public AddressDtoDefault saveAddress(AddressDtoDefault addressDto) {
        AddressModel addressModel = new AddressModel(addressDto);
        return new AddressDtoDefault(addressRepository.save(addressModel));
    }

    public AddressDtoDefault updateAddress(Long id, AddressDtoDefault addressDto) {
        Optional<AddressModel> existentAddressModelOptional = addressRepository.findById(id);

        if (existentAddressModelOptional.isPresent()) {
            AddressModel updatedAddressModel = new AddressModel(addressDto);
            BeanUtils.copyProperties(existentAddressModelOptional, updatedAddressModel);
            return new AddressDtoDefault(addressRepository.save(updatedAddressModel));
        } else {
            throw new ResourceNotFoundException("Resource: Address. Not found with id: " + id);
        }
    }

    public void deleteAddressById(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Resource: Address. Not found with id: " + id);
        }
    }
}
