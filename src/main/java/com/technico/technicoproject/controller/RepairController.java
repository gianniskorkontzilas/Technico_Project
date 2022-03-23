package com.technico.technicoproject.controller;
import com.technico.technicoproject.dto.PropertyDto;
import com.technico.technicoproject.dto.RepairDto;
import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.model.Repair;
import com.technico.technicoproject.service.RepairService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/repair/")

public class RepairController {
    private RepairService repairService;
    @PostMapping("/create")
    public ResponseResult<Repair> create(@RequestBody Repair repair) {
        return repairService.createRepair(repair);
    }

    @PutMapping("{caseId}")
    public ResponseResult<Repair> update(@PathVariable("caseId") Long caseId, @RequestBody Repair repair) {
        return repairService.updateRepair(caseId, repair);
    }

    @GetMapping("{caseId}")
    public ResponseResult<RepairDto> findByCaseId(@PathVariable("caseId") Long caseId){
        return repairService.readRepair(caseId);
    }
    @GetMapping("date/{date}")
    public ResponseResult<List<RepairDto>> findByDate(@PathVariable("date") String date) {
        return repairService.readRepairsByDate(date);
    }

    @GetMapping("dates/{fromDate}/{toDate}")
    public ResponseResult<List<RepairDto>> findByDates(@PathVariable("fromDate") String fromDate,@PathVariable("toDate") String toDate) {
        return repairService.readRepairsByDate(fromDate, toDate);
    }

    @GetMapping("vat/{vatNumber}")
    public ResponseResult<List<RepairDto>> findRepairsByVatNumber(@PathVariable("vatNumber") String vatNumber) {
        return repairService.readRepairsByVatNumber(vatNumber);
    }

    @DeleteMapping("{caseId}")
    public ResponseResult<Boolean> delete(@PathVariable("caseId") Long caseId) {
        return repairService.deleteRepair(caseId);
    }

}


