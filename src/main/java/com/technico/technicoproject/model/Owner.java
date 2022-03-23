package com.technico.technicoproject.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * This is the Owner Model
 */
@Data
@Entity
@Table(name = "Owners")
public class Owner {
    @Id
    private String vatNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
}
