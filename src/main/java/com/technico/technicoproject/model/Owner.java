package com.technico.technicoproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Property> properties;
}
