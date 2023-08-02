package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.EdgeDtoDefault;
import com.teclinecg.noxus.services.EdgeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/edge")
public class EdgeController {

    @Autowired
    private EdgeService edgeService;

    @GetMapping
    @ApiOperation(value = "http://localhost:8080/edge?page=0&size=2", notes = "Returns Edge Paginated")
    public ResponseEntity<Page<EdgeDtoDefault>> findAllEdgesPaginated(Pageable pageable) {
        Page<EdgeDtoDefault> edges = edgeService.findAllEdgesPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(edges);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "http://localhost:8080/edge/{edgeId}", notes = "Returns edge Selected By Id")
    public ResponseEntity<EdgeDtoDefault> findEdgeById(@PathVariable Long id) {
        EdgeDtoDefault edge = edgeService.findEdgeById(id);
        return ResponseEntity.ok().body(edge);
    }

    @PostMapping
    @ApiOperation(value = "http://localhost:8080/edge", notes = "Save a new edge")
    public ResponseEntity<EdgeDtoDefault> saveEdge( @RequestBody EdgeDtoDefault edgeDto) {
        edgeDto = edgeService.saveEdge(edgeDto);
        return ResponseEntity.ok().body(edgeDto);
    }

    @PutMapping
    @ApiOperation(value = "http://localhost:8080/edge/{edgeId}", notes = "Update an existing edge")
    public ResponseEntity<EdgeDtoDefault> updateEdgeById(@PathVariable Long id,  @RequestBody EdgeDtoDefault edgeDto) {
        edgeDto = edgeService.updateEdge(id, edgeDto);
        return ResponseEntity.ok().body(edgeDto);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation(value = "http://localhost:8080/edge/{edgeId}", notes = "Delete an existing edge")
    public ResponseEntity<EdgeDtoDefault> deleteEdgeById(@PathVariable Long id) {
        edgeService.deleteEdgeById(id);
        return ResponseEntity.noContent().build();
    }
}
