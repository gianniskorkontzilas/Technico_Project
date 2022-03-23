package com.technico.technicoproject.controller;

import com.technico.technicoproject.dto.RepairDto;
import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.model.Repair;
import com.technico.technicoproject.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a Rest Controller which receive the Requests call The Repair Service (Dependency Injection)
 */

@AllArgsConstructor
@RestController
@RequestMapping("/repair/")

public class RepairController {
    private RepairService repairService;

    /**
     * This Controller method call a method of RepairService to Create a Repair
     *
     * @param repair
     * @return ResponseResult<Repair>
     */
    @PostMapping("/create")
    public ResponseResult<Repair> create(@RequestBody Repair repair) {
        return repairService.createRepair(repair);
    }

    /**
     * This Controller method call a method of RepairService to Update the Repair with this caseId
     *
     * @param caseId
     * @return ResponseResult<Repair>
     */
    @PutMapping("{caseId}")
    public ResponseResult<Repair> update(@PathVariable("caseId") Long caseId, @RequestBody Repair repair) {
        return repairService.updateRepair(caseId, repair);
    }

    /**
     * This Controller method call a method of RepairService to get the Repair with this caseId
     *
     * @param caseId
     * @return ResponseResult<RepairDto>
     */
    @GetMapping("{caseId}")
    public ResponseResult<RepairDto> findByCaseId(@PathVariable("caseId") Long caseId) {
        return repairService.readRepair(caseId);
    }

    /**
     * This Controller method call a method of RepairService to get a list of Repairs with this date
     *
     * @param date
     * @return ResponseResult<List < RepairDto>>
     */
    @GetMapping("date/{date}")
    public ResponseResult<List<RepairDto>> findByDate(@PathVariable("date") String date) {
        return repairService.readRepairsByDate(date);
    }

    /**
     * This Controller method call a method of RepairService to get a list of Repairs with this range of date
     *
     * @param fromDate
     * @param toDate
     * @return ResponseResult<List < RepairDto>>
     */
    @GetMapping("dates/{fromDate}/{toDate}")
    public ResponseResult<List<RepairDto>> findByDates(@PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) {
        return repairService.readRepairsByDate(fromDate, toDate);
    }

    /**
     * This Controller method call a method of RepairService to get the Repair with this vatNumber
     *
     * @param vatNumber
     * @return ResponseResult<List < RepairDto>>
     */
    @GetMapping("vat/{vatNumber}")
    public ResponseResult<List<RepairDto>> findRepairsByVatNumber(@PathVariable("vatNumber") String vatNumber) {
        return repairService.readRepairsByVatNumber(vatNumber);
    }

    /**
     * This Controller method call a method of RepairService to delete the Repair with this caseId
     *
     * @param caseId
     * @return ResponseResult<Boolean>
     */
    @DeleteMapping("{caseId}")
    public ResponseResult<Boolean> delete(@PathVariable("caseId") Long caseId) {
        return repairService.deleteRepair(caseId);
    }
}


