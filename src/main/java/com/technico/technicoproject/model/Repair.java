package com.technico.technicoproject.model;
import com.technico.technicoproject.enumeration.RepairStatus;
import com.technico.technicoproject.enumeration.RepairType;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Data
@Table(name = "Repairs")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseId;
    private LocalDate dateOfRegistration;
    private LocalDate dateOfCompletion;
    @ManyToOne
    private Property property;
    private RepairStatus repairStatus;
    private RepairType typeOfRepair;
    private BigDecimal cost;
    @ManyToOne
    private Owner owner;
    private String description;
}

