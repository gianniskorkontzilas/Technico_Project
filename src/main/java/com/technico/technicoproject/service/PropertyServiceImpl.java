package com.technico.technicoproject.service;

import com.technico.technicoproject.dto.PropertyDto;
import com.technico.technicoproject.model.Owner;
import com.technico.technicoproject.model.Property;
import com.technico.technicoproject.repository.OwnerRepository;
import com.technico.technicoproject.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private OwnerRepository ownerRepository;
    private PropertyRepository propertyRepository;
    @Override
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }


    @Override
    public Property readPropertyByPropertyNumber(String propertyNumber) {
        return propertyRepository.findById(propertyNumber).get();
    }

    @Override
    public List<PropertyDto> readPropertyByVatNumber(String vatNumber) {
        Optional<Owner> ownerOpt = ownerRepository.findById(vatNumber);
        if(ownerOpt.isEmpty()) return null;
        List<Property> properties = propertyRepository.getPropertiesFromOwner(vatNumber);
        return properties.stream().map(propertydto -> new PropertyDto(
                propertydto.getPropertyNumber(),
                propertydto.getAddress(),
                propertydto.getConstructionYear(),
                propertydto.getPropertyType())).toList();

    }


    @Override
    public Property updateProperty(String propertyNumber, Property property) {
        Optional<Property> propertyDb = propertyRepository.findById(propertyNumber);
        if (propertyDb.isEmpty())
            return null;
        propertyDb.get().setPropertyNumber(property.getPropertyNumber());
        propertyDb.get().setAddress(property.getAddress());
        propertyDb.get().setConstructionYear(property.getConstructionYear());
        propertyDb.get().setPropertyType(property.getPropertyType());
        propertyDb.get().setOwner(property.getOwner());
        return propertyRepository.save(propertyDb.get());
    }

    @Override
    public boolean deleteProperty(String propertyNumber) {
        Optional<Property> propertyDb = propertyRepository.findById(propertyNumber);
        if (propertyDb.isEmpty())
            return false;
        propertyRepository.delete(propertyDb.get());
        return true;
    }
}
