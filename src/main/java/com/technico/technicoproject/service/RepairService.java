package com.technico.technicoproject.service;
import com.technico.technicoproject.exception.RepairException;
import com.technico.technicoproject.model.Repair;
import java.util.List;
public interface RepairService {
    Repair createRepair(String propertyAddress,String vatNumber,Repair repair) throws RepairException;
    Repair updateRepair(Long caseId ,Repair repair );
    List<Repair> readRepairsByDate(String fromDate, String toDate);
    List<Repair> readRepairsByVatNumber(String vatNumber);
    boolean deleteRepair(Long caseId) ;
}