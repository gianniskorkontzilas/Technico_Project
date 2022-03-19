package com.technico.technicoproject.controller;

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
    @PostMapping("/create")
    public Property create(@RequestBody Property property)
    {
        return propertyService.createProperty(property);
    }

    //Find property by propertyNumber
    @GetMapping("/propertynumber/{propertynumber}" )
    public Property findByPropertyNumber (@PathVariable("propertyNumber") String propertyNumber){
        return propertyService.readPropertyByPropertyNumber(propertyNumber);
    }

    //Find properties by Owner number
    @GetMapping("/vat/{ownerVatNumber}" )
    public List<PropertyDto> findByVatNumber (@PathVariable("ownerVatNumber") String ownerVat){
        return propertyService.readPropertyByVatNumber(ownerVat);
    }

    //Update property
    @PutMapping("/{propertyNumber}/update")
    public Property update(@PathVariable("propertyNumber") String propertyNumber,@RequestBody Property property )
    {
        return PropertyService.updateProperty(propertyNumber,property);
    }


    //Delete property
    @DeleteMapping("/{propertyNumber}/delete" )
    public boolean delete (@PathVariable("propertyNumber") String propertyNumber){
        return PropertyService.deleteProperty(propertyNumber);
    }

}