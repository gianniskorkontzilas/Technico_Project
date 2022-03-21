package com.technico.technicoproject.repository;

import com.technico.technicoproject.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, String> {

    @Query( value ="SELECT pr FROM Property pr WHERE pr.ownerVat = :vatNumber")
    List<Property> getPropertiesFromOwner(String vatNumber);
    boolean existsPropertyByOwnerVat(String vatNumber);
    Property findPropertyByAddress(String addressProperty);
}
