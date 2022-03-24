package com.technico.technicoproject.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.technico.technicoproject.dto.*;
import com.technico.technicoproject.model.Owner;
import com.technico.technicoproject.model.Property;
import com.technico.technicoproject.model.Repair;
import com.technico.technicoproject.repository.OwnerRepository;
import com.technico.technicoproject.repository.PropertyRepository;
import com.technico.technicoproject.repository.RepairRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class GeneralServiceImpl implements GeneralService{
    private OwnerService ownerService;
    private RepairRepository repairRepository;
    private PropertyRepository propertyRepository;
    private OwnerRepository ownerRepository;


    @Override
    public ResponseResult<List<OwnerDto>> showAll(){
        List<Owner> owners = ownerRepository.findAll();
        List<OwnerDto> ownersDto = new ArrayList<>();
        for(Owner owner : owners){
            OwnerDto ownerDto = new OwnerDto();
            List<Property> properties = propertyRepository.findPropertiesByOwner(owner);
            List<PropertiesDto> propertyDtos = new ArrayList<>();
            List<RepairDto> repairDtos = new ArrayList<>();
            List<Repair> repairs = repairRepository.findRepairsByVatNumber(owner.getVatNumber());


            ownerDto.setVatNumber(owner.getVatNumber());
            ownerDto.setFirstName(owner.getFirstName());
            ownerDto.setLastName(owner.getLastName());
            ownerDto.setAddress(owner.getAddress());
            ownerDto.setPhoneNumber(owner.getPhoneNumber());
            ownerDto.setEmail(owner.getEmail());
            ownerDto.setUsername(owner.getUsername());
            ownerDto.setPassword(owner.getPassword());
            for(Property property : properties){


                PropertiesDto propertyDto = new PropertiesDto();
                propertyDto.setPropertyNumber(property.getPropertyNumber());
                propertyDto.setAddress(property.getAddress());
                propertyDto.setVatNumber(property.getOwner().getVatNumber());
                propertyDto.setConstructionYear(property.getConstructionYear());
                propertyDto.setPropertyType(property.getPropertyType());
                for(Repair repair : repairs){
                    RepairDto repairDto = new RepairDto();
                    repairDto.setCaseId(repair.getCaseId());
                    repairDto.setDateOfRegistration(repair.getDateOfRegistration());
                    repairDto.setDateOfCompletion(repair.getDateOfCompletion());
                    repairDto.setVatNumber(repair.getVatNumber());
                    repairDto.setAddress(repair.getProperty().getAddress());
                    repairDto.setRepairStatus(repair.getRepairStatus());
                    repairDto.setTypeOfRepair(repair.getTypeOfRepair());
                    repairDto.setCost(repair.getCost());
                    repairDto.setDescription(repair.getDescription());
                    repairDtos.add(repairDto);
                }
                propertyDto.setRepairs(repairDtos);
                propertyDtos.add(propertyDto);
            }
            ownerDto.setProperties(propertyDtos);
            ownersDto.add(ownerDto);
        }
        return new ResponseResult(ownersDto, ResponseStatus.SUCCESS, "Returned all info.");
    }

    @Override
    public ResponseResult<Boolean> deleteAll(){
        repairRepository.deleteAll();
        propertyRepository.deleteAll();
        ownerRepository.deleteAll();
        return new ResponseResult(true,ResponseStatus.SUCCESS,"All information deleted");
    }

}
