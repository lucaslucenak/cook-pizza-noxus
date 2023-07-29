package com.teclinecg.noxus.dtos;

import com.teclinecg.noxus.models.AuditModel;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AuditDtoDefault {

    @NotNull
    private Long id;
    private String client;

    @NotNull
    private String body;

    public AuditDtoDefault() {
    }

    public AuditDtoDefault(AuditModel auditModel) {
        BeanUtils.copyProperties(auditModel, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditDtoDefault that = (AuditDtoDefault) o;
        return id.equals(that.id) && Objects.equals(client, that.client) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, body);
    }
}
