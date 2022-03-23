package com.technico.technicoproject.repository;
import com.technico.technicoproject.model.Property;
import com.technico.technicoproject.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Repository
public interface RepairRepository extends JpaRepository<Repair,Long> {
    Repair findRepairByCaseId(Long caseId);
    List<Repair> findRepairsByDateOfRegistration(LocalDate date);
    List<Repair> findRepairsByDateOfRegistrationBetween(LocalDate startDate, LocalDate endDate);
    List<Repair> findRepairByProperty(Property property);
    List<Repair> findRepairsByVatNumber(String vatNumber);
}
