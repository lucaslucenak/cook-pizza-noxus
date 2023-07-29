package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.EdgeDtoDefault;
import com.teclinecg.noxus.exceptions.InvalidPageQuantityException;
import com.teclinecg.noxus.exceptions.InvalidRegisterQuantityException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.EdgeModel;
import com.teclinecg.noxus.repositories.EdgeRepository;
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
public class EdgeService {

    @Autowired
    private EdgeRepository edgeRepository;

    public EdgeDtoDefault findEdgeById(Long id) {
        Optional<EdgeModel> edgeOptional = edgeRepository.findById(id);

        if (edgeOptional.isPresent()) {
            return new EdgeDtoDefault(edgeOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Edge. Not found with id: " + id);
        }
    }

    public List<EdgeDtoDefault> findAllEdgesPaginated(Integer pagQtt, Integer registerQtt) {
        if (pagQtt < 1) {
            throw new InvalidPageQuantityException("Invalid Page Quantity. Must be greater than one");
        }
        if (registerQtt < 1) {
            throw new InvalidRegisterQuantityException("Invalid Register Quantity. Must be greater than one");
        }

        // Paginated JPA query
        Pageable pageRequest = PageRequest.of(pagQtt, registerQtt);
        Page<EdgeModel> result = edgeRepository.findAll(pageRequest);

        // Converting Models to DTOs
        List<EdgeModel> edgeModels = result.stream().toList();
        List<EdgeDtoDefault> dtos = new ArrayList<>();

        for (EdgeModel i : edgeModels) {
            dtos.add(new EdgeDtoDefault(i));
        }

        return dtos;
    }

    public EdgeDtoDefault saveEdge(@Valid EdgeDtoDefault drinkDto) {
        EdgeModel edgeModel = new EdgeModel(drinkDto);
        return new EdgeDtoDefault(edgeRepository.save(edgeModel));
    }

    public EdgeDtoDefault updateEdge(Long id, @Valid EdgeDtoDefault drinkDto) {
        Optional<EdgeModel> existentEdgeModelOptional = edgeRepository.findById(id);

        if (existentEdgeModelOptional.isPresent()) {
            EdgeModel updatedEdgeModel = new EdgeModel(drinkDto);
            BeanUtils.copyProperties(existentEdgeModelOptional, updatedEdgeModel);
            return new EdgeDtoDefault(edgeRepository.save(updatedEdgeModel));
        } else {
            throw new ResourceNotFoundException("Resource: Edge. Not found with id: " + id);
        }
    }

    public void deleteEdgeById(Long id) {
        if (edgeRepository.existsById(id)) {
            edgeRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Resource: Edge. Not found with id: " + id);
        }
    }
}
