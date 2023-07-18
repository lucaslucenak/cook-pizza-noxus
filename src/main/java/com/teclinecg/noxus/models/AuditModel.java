package com.teclinecg.noxus.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "audit")
public class AuditModel {

    @Id
    @GeneratedValue
    private Long id;

    private String client;
    private String body;

    public AuditModel() {
    }

    public AuditModel(Long id, String client, String body) {
        this.id = id;
        this.client = client;
        this.body = body;
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
        AuditModel that = (AuditModel) o;
        return id.equals(that.id) && Objects.equals(client, that.client) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, body);
    }
}
