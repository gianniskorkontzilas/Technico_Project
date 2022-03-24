package com.technico.technicoproject.dto;

import com.technico.technicoproject.model.Property;
import com.technico.technicoproject.model.Repair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    private String vatNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    private String username;
    private String password;
    private List<PropertiesDto> properties;

}
