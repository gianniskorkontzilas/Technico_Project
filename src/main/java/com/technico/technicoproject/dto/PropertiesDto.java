package com.technico.technicoproject.dto;

import com.technico.technicoproject.enumeration.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesDto {
    private String propertyNumber;
    private String address;
    private String vatNumber;
    private String constructionYear;
    private PropertyType propertyType;
    private List<RepairDto> repairs;
}