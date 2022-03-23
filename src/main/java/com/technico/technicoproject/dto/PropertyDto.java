package com.technico.technicoproject.dto;

import com.technico.technicoproject.enumeration.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDto {
    private String propertyNumber;
    private String address;
    private String vatNumber;
    private String constructionYear;
    private PropertyType propertyType;
}
