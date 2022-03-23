package com.technico.technicoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.technico.technicoproject.enumeration.PropertyType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Properties")
public class Property {
    @Id
    private String propertyNumber;
    private String address;
    @JsonInclude
    @Transient
    private String vatNumber;
    private String constructionYear;
    private PropertyType propertyType;

    @JsonIgnore
    @ManyToOne
    private Owner owner;
}
