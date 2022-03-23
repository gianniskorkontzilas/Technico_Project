package com.technico.technicoproject.controller;

import com.technico.technicoproject.dto.OwnerDto;
import com.technico.technicoproject.dto.PropertyDto;
import com.technico.technicoproject.dto.ResponseResult;
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
    public ResponseResult<PropertyDto> create(@RequestBody Property property)
    {
        return propertyService.createProperty(property);
    }
   @GetMapping("{propertyNumber}")
   public ResponseResult<PropertyDto> findByPropertyId(@PathVariable("propertyNumber") String propertyNumber){
        return propertyService.readPropertyByPropertyNumber(propertyNumber);
   }

    //Find properties by Owner number
    @GetMapping("vat/{vatNumber}")
    public ResponseResult<List<PropertyDto>> findByVatNumber(@PathVariable("vatNumber") String vatNumber){
        return propertyService.readPropertyByVatNumber(vatNumber);
    }

    //Update property
    @PutMapping("{propertyNumber}")
    public ResponseResult<PropertyDto> update(@PathVariable("propertyNumber") String propertyNumber,@RequestBody Property property )
    {
        return propertyService.updateProperty(propertyNumber,property);
    }


    //Delete property
    @DeleteMapping("{propertyNumber}" )
    public ResponseResult<Boolean> delete(@PathVariable("propertyNumber") String propertyNumber){
        return propertyService.deleteProperty(propertyNumber);
    }

}