package com.technico.technicoproject.service;

import com.technico.technicoproject.dto.PropertyDto;
import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.model.Property;

import java.util.List;

public interface PropertyService {
    ResponseResult<PropertyDto> createProperty(Property property);
    ResponseResult<PropertyDto> readPropertyByPropertyNumber(String propertyNumber);
    ResponseResult<List<PropertyDto>> readPropertyByVatNumber(String vatNumber);
    ResponseResult<PropertyDto> updateProperty(String propertyNumber , Property property);
    ResponseResult<Boolean> deleteProperty(String propertyNumber);
}
