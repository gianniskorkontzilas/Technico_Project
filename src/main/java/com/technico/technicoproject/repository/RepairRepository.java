package com.technico.technicoproject.repository;
import com.technico.technicoproject.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RepairRepository extends JpaRepository<Repair,Long> {
    List<Repair> findRepairsByOwnerVatNumber(String vatNumber);
}
