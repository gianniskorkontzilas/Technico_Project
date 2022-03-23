package com.technico.technicoproject.repository;

import com.technico.technicoproject.model.Owner;
import com.technico.technicoproject.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This is The Property Repository Interface which extend the JpaRepository with Parameters Type of  Property and
 * String because String is the Type of Primary Key of Property
 */
public interface PropertyRepository extends JpaRepository<Property, String> {
    List<Property> findPropertiesByAddress(String address);

    List<Property> findPropertiesByOwner(Owner owner);

    Property findPropertyByOwnerAndAddress(Owner owner, String address);

    Boolean existsByPropertyNumber(String propertyNumber);
}
