package com.technico.technicoproject.model;

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
    private String constructionYear;
    private PropertyType propertyType;
    @ManyToOne
    private Owner owner;
}
