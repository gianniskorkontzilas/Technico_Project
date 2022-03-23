package com.technico.technicoproject.service;

import com.technico.technicoproject.dto.PropertyDto;
import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.dto.ResponseStatus;
import com.technico.technicoproject.model.Owner;
import com.technico.technicoproject.model.Property;
import com.technico.technicoproject.repository.OwnerRepository;
import com.technico.technicoproject.repository.PropertyRepository;
import com.technico.technicoproject.repository.RepairRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private OwnerRepository ownerRepository;
    private PropertyRepository propertyRepository;
    private RepairRepository repairRepository;
    @Override
    public ResponseResult<PropertyDto> createProperty(Property property) {

        if(propertyRepository.existsById(property.getPropertyNumber())){
            return new ResponseResult(null, ResponseStatus.PROPERTY_NOT_CREATED,"Property number already used by another property");
        }

        Owner owner = ownerRepository.findOwnerByVatNumber(property.getVatNumber());

        if(owner==null){
            return new ResponseResult(null, ResponseStatus.PROPERTY_NOT_CREATED,"The VAT number entered for the owner of this property isn't used by any owner");
        }

        property.setOwner(owner);
        try {
            propertyRepository.save(property);
        }catch (Exception e){
            return new ResponseResult(null,ResponseStatus.PROPERTY_NOT_CREATED,"Property creation failed because of: "+ e);
        }
        return new ResponseResult(this.returnEnteredValues(property),ResponseStatus.SUCCESS,"Property successfully saved");

    }

    @Override
    public ResponseResult<PropertyDto> readPropertyByPropertyNumber(String propertyNumber) {
        Property property = propertyRepository.findById(propertyNumber).get();
        if(property==null){
            return new ResponseResult(null,ResponseStatus.PROPERTY_NOT_FOUND,"Cannot find property with "+ propertyNumber+" as property number");
        }
        return new ResponseResult(this.returnEnteredValues(property),ResponseStatus.SUCCESS,"Property found successfully");
    }

    @Override
    public ResponseResult<List<PropertyDto>> readPropertyByVatNumber(String vatNumber) {
        Optional<Owner> ownerOpt = ownerRepository.findById(vatNumber);
        if(ownerOpt.isEmpty()) {
            return new ResponseResult(null,ResponseStatus.PROPERTY_NOT_FOUND,"Search for properties with this VAT "+ vatNumber +" ,failed because no owner exists");
        }

        List<Property> properties = propertyRepository.findPropertiesByOwner(ownerRepository.findOwnerByVatNumber(vatNumber));
        return new ResponseResult(this.returnEnteredValuesList(properties),ResponseStatus.SUCCESS,"Successfully returned all properties belonging to this "+ vatNumber+ " VAT number");

    }

    @Override
    public ResponseResult<PropertyDto> updateProperty(String propertyNumber, Property property) {
        Optional<Property> propertyDb = propertyRepository.findById(propertyNumber);
        if (propertyDb.isEmpty()){
            return new ResponseResult(null,ResponseStatus.PROPERTY_NOT_UPDATED,"Cannot find property with "+ propertyNumber+" as property number");
        }

        propertyDb.get().setPropertyNumber(property.getPropertyNumber());
        propertyDb.get().setAddress(property.getAddress());
        propertyDb.get().setConstructionYear(property.getConstructionYear());
        propertyDb.get().setPropertyType(property.getPropertyType());
        propertyDb.get().setOwner(ownerRepository.findOwnerByVatNumber(property.getVatNumber()));
        propertyRepository.save(propertyDb.get());
        return new ResponseResult(this.returnEnteredValues(propertyDb.get()),ResponseStatus.SUCCESS,"Successfully updated property details");
    }

    @Override
    public ResponseResult<Boolean> deleteProperty(String propertyNumber) {
        if(!propertyRepository.existsByPropertyNumber(propertyNumber)){
            return new ResponseResult(false,ResponseStatus.PROPERTY_NOT_DELETED,"Delete operation failed because property with number: "+ propertyNumber+" doesn't exist");
        }

        if(repairRepository.findRepairByProperty(propertyRepository.getById(propertyNumber)).size()!=0){
            return new ResponseResult(false,ResponseStatus.PROPERTY_NOT_DELETED,"Delete failed because property has repairs");
        }
        propertyRepository.deleteById(propertyNumber);
        return new ResponseResult(true,ResponseStatus.SUCCESS,"Deleted property successfully");
    }

    public PropertyDto returnEnteredValues(Property property){
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setPropertyNumber(property.getPropertyNumber());
        propertyDto.setAddress(property.getAddress());
        propertyDto.setVatNumber(property.getOwner().getVatNumber());
        propertyDto.setConstructionYear(property.getConstructionYear());
        propertyDto.setPropertyType(property.getPropertyType());
        return propertyDto;
    }

    public List<PropertyDto> returnEnteredValuesList(List<Property> properties){
        List<PropertyDto> propertyDtos = new ArrayList<>();
        for(Property property : properties){
            propertyDtos.add(this.returnEnteredValues(property));
        }
        return propertyDtos;
    }
}
