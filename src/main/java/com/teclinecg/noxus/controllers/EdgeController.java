package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.EdgeDtoDefault;
import com.teclinecg.noxus.services.EdgeService;
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
    // Example: http://localhost:8080/edge?page=0&size=2
    public ResponseEntity<Page<EdgeDtoDefault>> findAllEdgesPaginated(Pageable pageable) {
        Page<EdgeDtoDefault> edges = edgeService.findAllEdgesPaginated(pageable);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Requested-Pag-Qtt", Integer.toString(pageable.getPageNumber()));
        headers.add("Requested-Register-Qtt", Integer.toString(pageable.getPageSize()));

        return ResponseEntity.ok().headers(headers).body(edges);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EdgeDtoDefault> findEdgeById(@PathVariable Long id) {
        EdgeDtoDefault edge = edgeService.findEdgeById(id);
        return ResponseEntity.ok().body(edge);
    }

    @PostMapping
    public ResponseEntity<EdgeDtoDefault> saveEdge(@Validated @RequestBody EdgeDtoDefault edgeDto) {
        edgeDto = edgeService.saveEdge(edgeDto);
        return ResponseEntity.ok().body(edgeDto);
    }

    @PutMapping
    public ResponseEntity<EdgeDtoDefault> updateEdgeById(@PathVariable Long id, @Validated @RequestBody EdgeDtoDefault edgeDto) {
        edgeDto = edgeService.updateEdge(id, edgeDto);
        return ResponseEntity.ok().body(edgeDto);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<EdgeDtoDefault> deleteEdgeById(@PathVariable Long id) {
        edgeService.deleteEdgeById(id);
        return ResponseEntity.noContent().build();
    }
}
