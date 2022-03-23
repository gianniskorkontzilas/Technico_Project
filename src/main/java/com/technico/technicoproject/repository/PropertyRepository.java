package com.technico.technicoproject.repository;

import com.technico.technicoproject.model.Owner;
import com.technico.technicoproject.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, String> {
    List<Property> findPropertiesByAddress(String address);
    List<Property> findPropertiesByOwner(Owner owner);
    Property findPropertyByOwnerAndAddress(Owner owner, String address);
    Boolean existsByPropertyNumber(String propertyNumber);
}
