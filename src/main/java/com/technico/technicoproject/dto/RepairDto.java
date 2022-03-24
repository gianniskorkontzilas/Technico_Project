package com.technico.technicoproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.technico.technicoproject.enumeration.RepairStatus;
import com.technico.technicoproject.enumeration.RepairType;
import com.technico.technicoproject.model.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairDto {

    private Long caseId;
    private LocalDate dateOfRegistration;
    private LocalDate dateOfCompletion;
    private String address;
    private String vatNumber;
    private RepairStatus repairStatus;
    private RepairType typeOfRepair;
    private BigDecimal cost;
    private String description;
}
