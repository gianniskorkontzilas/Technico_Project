package com.technico.technicoproject.controller;

import com.technico.technicoproject.dto.OwnerDto;
import com.technico.technicoproject.dto.PropertyDto;
import com.technico.technicoproject.model.Property;
import com.technico.technicoproject.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/property/")
public class PropertyController {
    private PropertyService propertyService;
    //Create new property
    @PostMapping("create")
    public Property create(@RequestBody Property property)
    {
        return propertyService.createProperty(property);
    }

   @GetMapping("{propertyNumber}")
   public Property findByPropertyId(@PathVariable("propertyNumber") String propertyNumber){
        return propertyService.readPropertyByPropertyNumber(propertyNumber);
   }

    //Find properties by Owner number
    @GetMapping("vat/{ownerVatNumber}")
    public List<PropertyDto> findByVatNumber(@PathVariable("ownerVatNumber") String ownerVat){
        return propertyService.readPropertyByVatNumber(ownerVat);
    }

    //Update property
    @PutMapping("update/{propertyNumber}")
    public Property update(@PathVariable("propertyNumber") String propertyNumber,@RequestBody Property property )
    {
        return propertyService.updateProperty(propertyNumber,property);
    }


    //Delete property
    @DeleteMapping("delete/{propertyNumber}" )
    public boolean delete(@PathVariable("propertyNumber") String propertyNumber){
        return propertyService.deleteProperty(propertyNumber);
    }

}