package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.AddressDtoDefault;
import com.teclinecg.noxus.dtos.OrderDtoDefault;
import com.teclinecg.noxus.exceptions.InvalidPageQuantityException;
import com.teclinecg.noxus.exceptions.InvalidRegisterQuantityException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.AddressModel;
import com.teclinecg.noxus.models.OrderModel;
import com.teclinecg.noxus.repositories.AddressRepository;
import com.teclinecg.noxus.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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

    public List<AddressDtoDefault> findAllAddressesPaginated(Integer pagQtt, Integer registerQtt) {
        if (pagQtt < 1) {
            throw new InvalidPageQuantityException("Invalid Page Quantity. Must be greater than one");
        }
        if (registerQtt < 1) {
            throw new InvalidRegisterQuantityException("Invalid Register Quantity. Must be greater than one");
        }

        // Paginated JPA query
        Pageable pageRequest = PageRequest.of(pagQtt, registerQtt);
        Page<AddressModel> result = addressRepository.findAll(pageRequest);

        // Converting Models to DTOs
        List<AddressModel> addressModels = result.stream().toList();
        List<AddressDtoDefault> dtos = new ArrayList<>();

        for (AddressModel i : addressModels) {
            dtos.add(new AddressDtoDefault(i));
        }

        return dtos;
    }

    public AddressDtoDefault saveAddress(@Valid AddressDtoDefault addressDto) {
        AddressModel addressModel = new AddressModel(addressDto);
        return new AddressDtoDefault(addressRepository.save(addressModel));
    }

    public AddressDtoDefault updateAddress(Long id, @Valid AddressDtoDefault addressDto) {
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
