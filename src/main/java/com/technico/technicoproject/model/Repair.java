package com.technico.technicoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.technico.technicoproject.enumeration.RepairStatus;
import com.technico.technicoproject.enumeration.RepairType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * This is the Repair Model
 */
@Entity
@Data
@Table(name = "Repairs")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;
    private LocalDate dateOfRegistration;
    private LocalDate dateOfCompletion;
    @JsonInclude
    @Transient
    private String address;
    private String vatNumber;
    private RepairStatus repairStatus;
    private RepairType typeOfRepair;
    private BigDecimal cost;
    private String description;
    @JsonIgnore
    @ManyToOne
    private Property property;
}

