package com.teclinecg.noxus.controllers;

import com.teclinecg.noxus.dtos.AuditDtoDefault;
import com.teclinecg.noxus.services.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @PostMapping
    public ResponseEntity<AuditDtoDefault> saveAudit(@Validated @RequestBody AuditDtoDefault auditDto) {
        auditDto = auditService.saveAudit(auditDto);
        return ResponseEntity.ok().body(auditDto);
    }
}
