package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.EdgeDto;
import com.teclinecg.noxus.exceptions.InvalidPageNumberException;
import com.teclinecg.noxus.exceptions.InvalidPageRegisterSizeException;
import com.teclinecg.noxus.exceptions.ResourceNotFoundException;
import com.teclinecg.noxus.models.EdgeModel;
import com.teclinecg.noxus.repositories.EdgeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class EdgeService {

    @Autowired
    private EdgeRepository edgeRepository;

    public EdgeDto findEdgeById(Long id) {
        Optional<EdgeModel> edgeOptional = edgeRepository.findById(id);

        if (edgeOptional.isPresent()) {
            return new EdgeDto(edgeOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Edge. Not found with id: " + id);
        }
    }

    public Page<EdgeDto> findAllEdgesPaginated(Pageable pageable) {
        if (pageable.getPageNumber() < 0) {
            throw new InvalidPageNumberException("Invalid Page Number. Must be greater or equal than zero");
        }
        if (pageable.getPageSize() < 1) {
            throw new InvalidPageRegisterSizeException("Invalid Register Size. Must be greater than zero");
        }

        // Paginated JPA query
        Page<EdgeModel> pagedEdges = edgeRepository.findAll(pageable);

        return pagedEdges.map(EdgeDto::new);
    }

    public EdgeDto saveEdge(EdgeDto edgeDto) {
        EdgeModel edgeModel = new EdgeModel(edgeDto);
        return new EdgeDto(edgeRepository.save(edgeModel));
    }

    public EdgeDto updateEdge(Long id, EdgeDto edgeDto) {
        Optional<EdgeModel> existentEdgeModelOptional = edgeRepository.findById(id);

        if (existentEdgeModelOptional.isPresent()) {
            EdgeModel updatedEdgeModel = new EdgeModel(edgeDto);
            BeanUtils.copyProperties(existentEdgeModelOptional, updatedEdgeModel);
            return new EdgeDto(edgeRepository.save(updatedEdgeModel));
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
