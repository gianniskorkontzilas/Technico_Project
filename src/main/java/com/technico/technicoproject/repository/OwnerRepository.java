package com.technico.technicoproject.repository;

import com.technico.technicoproject.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, String> {
    Owner findOwnerByVatNumber(String vatNumber);
    Owner findOwnerByEmail(String email);
    boolean existsOwnersByVatNumber(String vatNumber);
}

