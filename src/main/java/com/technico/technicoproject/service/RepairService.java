package com.technico.technicoproject.service;
import com.technico.technicoproject.dto.RepairDto;
import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.model.Repair;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.List;
public interface RepairService {
    ResponseResult<Repair> createRepair(Repair repair);
    ResponseResult<Repair> updateRepair(Long caseId ,Repair repair );
    ResponseResult<RepairDto> readRepair(Long caseId);
    ResponseResult<List<RepairDto>> readRepairsByDate(String dateString);
    ResponseResult<List<RepairDto>> readRepairsByDate(String  fromDate, String toDate);
    ResponseResult<List<RepairDto>> readRepairsByVatNumber(String vatNumber);
    ResponseResult<Boolean> deleteRepair(Long caseId) ;
}