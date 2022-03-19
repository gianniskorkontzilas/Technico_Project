package com.technico.technicoproject.repository;

import com.technico.technicoproject.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, String> {
    Property findPropertyByEmail(String email);
    boolean existsPropertyByVatNumber(String vatNumber);
}
