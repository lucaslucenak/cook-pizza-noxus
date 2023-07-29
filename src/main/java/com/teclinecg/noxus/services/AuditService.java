package com.teclinecg.noxus.services;

import com.teclinecg.noxus.dtos.AuditDtoDefault;
import com.teclinecg.noxus.models.AuditModel;
import com.teclinecg.noxus.repositories.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class AuditService {

    @Autowired
    private AuditRepository auditRepository;

    public AuditDtoDefault saveAudit(@Valid AuditDtoDefault auditDto) {
        AuditModel auditModel = new AuditModel(auditDto);
        return new AuditDtoDefault(auditRepository.save(auditModel));
    }
}
