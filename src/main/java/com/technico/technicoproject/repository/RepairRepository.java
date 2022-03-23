package com.technico.technicoproject.repository;

import com.technico.technicoproject.model.Property;
import com.technico.technicoproject.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * This is The Repair Repository Interface which extend the JpaRepository with Parameters  Type of Repair and
 * Long because Long is the Type of Primary Key of Repair
 */

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {
    Repair findRepairByCaseId(Long caseId);

    List<Repair> findRepairsByDateOfRegistration(LocalDate date);

    List<Repair> findRepairsByDateOfRegistrationBetween(LocalDate startDate, LocalDate endDate);

    List<Repair> findRepairByProperty(Property property);

    List<Repair> findRepairsByVatNumber(String vatNumber);
}
