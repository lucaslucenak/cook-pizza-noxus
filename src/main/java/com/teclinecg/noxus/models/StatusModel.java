package com.teclinecg.noxus.models;

import com.teclinecg.noxus.enums.StatusEnum;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "status")
public class StatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private StatusEnum status;

    public StatusModel() {
    }

    public StatusModel(Long id, StatusEnum status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusModel that = (StatusModel) o;
        return id.equals(that.id) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
