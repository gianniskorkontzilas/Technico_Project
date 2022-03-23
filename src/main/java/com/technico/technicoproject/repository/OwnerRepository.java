package com.technico.technicoproject.repository;

import com.technico.technicoproject.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is The Owner Repository Interface which extend the JpaRepository with Parameters Type of Owner and
 * String because String is the Type of Primary Key of Owner
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, String> {
    Owner findOwnerByVatNumber(String vatNumber);

    Owner findOwnerByEmail(String email);

    boolean existsOwnersByVatNumber(String vatNumber);

    boolean existsByEmail(String email);
}

