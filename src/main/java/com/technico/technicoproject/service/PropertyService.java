package com.technico.technicoproject.service;


import com.technico.technicoproject.model.Property;

import java.util.List;

public interface PropertyService {
    Property createProperty(Property property);
    Property readPropertyByPropertyNumber(String propertyNumber);
    List<Property> readPropertyByVatNumber(String vatNumber);
    Property updateProperty(String propertyNumber , Property property);
    boolean deleteProperty(String propertyNumber);

}
