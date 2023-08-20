package com.teclinecg.noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teclinecg.noxus.enums.NeighbourhoodEnum;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Table(name = "neighbourhood")
@EntityListeners(AuditingEntityListener.class) // Able to register created_at and updated_at
public class NeighbourhoodModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NeighbourhoodEnum neighbourhood;

    @OneToMany(mappedBy = "neighbourhood", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AddressModel> addresses;

    @OneToMany(mappedBy = "neighbourhood", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DeliveryTaxModel> deliveryTaxes;

    @Column(nullable = false, updatable = false)
    @CreatedDate // Auto fill
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @LastModifiedDate // Auto fill
    private LocalDateTime updatedAt;

    public NeighbourhoodModel() {
    }

    public NeighbourhoodModel(NeighbourhoodEnum neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public NeighbourhoodModel(Long id, NeighbourhoodEnum neighbourhood, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.neighbourhood = neighbourhood;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public NeighbourhoodModel(Long id, NeighbourhoodEnum neighbourhood, List<AddressModel> addresses, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.neighbourhood = neighbourhood;
        this.addresses = addresses;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public NeighbourhoodModel(Long id, NeighbourhoodEnum neighbourhood, List<AddressModel> addresses, List<DeliveryTaxModel> deliveryTaxes, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.neighbourhood = neighbourhood;
        this.addresses = addresses;
        this.deliveryTaxes = deliveryTaxes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NeighbourhoodEnum getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(NeighbourhoodEnum neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NeighbourhoodModel that = (NeighbourhoodModel) o;
        return Objects.equals(id, that.id) && neighbourhood == that.neighbourhood && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, neighbourhood, createdAt, updatedAt);
    }
}
