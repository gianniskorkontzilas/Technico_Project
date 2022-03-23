package com.technico.technicoproject.controller;

import com.technico.technicoproject.dto.PropertyDto;
import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.model.Property;
import com.technico.technicoproject.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a Rest Controller which receive the Requests call The Property Service (Dependency Injection)
 */
@RestController
@AllArgsConstructor
@RequestMapping("/property/")
public class PropertyController {
    private PropertyService propertyService;

    /**
     * This Controller method call a method of PropertyService to Create a Property
     *
     * @param property
     * @return ResponseResult<PropertyDto>
     */
    @PostMapping("create")
    public ResponseResult<PropertyDto> create(@RequestBody Property property) {
        return propertyService.createProperty(property);
    }

    /**
     * This Controller method call a method of PropertyService to get the Property with this Property Number
     *
     * @param propertyNumber
     * @return ResponseResult<PropertyDto>
     */
    @GetMapping("{propertyNumber}")
    public ResponseResult<PropertyDto> findByPropertyId(@PathVariable("propertyNumber") String propertyNumber) {
        return propertyService.readPropertyByPropertyNumber(propertyNumber);
    }

    /**
     * This Controller method call a method of PropertyService to get the Property with this vatNumber
     *
     * @param vatNumber
     * @return ResponseResult<List < PropertyDto>>
     */
    @GetMapping("vat/{vatNumber}")
    public ResponseResult<List<PropertyDto>> findByVatNumber(@PathVariable("vatNumber") String vatNumber) {
        return propertyService.readPropertyByVatNumber(vatNumber);
    }

    /**
     * This Controller method call a method of PropertyService to Upate the Property with this propertyNumber
     *
     * @param propertyNumber
     * @return ResponseResult<PropertyDto>
     */
    @PutMapping("{propertyNumber}")
    public ResponseResult<PropertyDto> update(@PathVariable("propertyNumber") String propertyNumber, @RequestBody Property property) {
        return propertyService.updateProperty(propertyNumber, property);
    }

    /**
     * This Controller method call a method of PropertyService to Delete the Property with this propertyNumber
     *
     * @param propertyNumber
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("{propertyNumber}")
    public ResponseResult<Boolean> delete(@PathVariable("propertyNumber") String propertyNumber) {
        return propertyService.deleteProperty(propertyNumber);
    }
}