package com.technico.technicoproject.controller;
import com.technico.technicoproject.exception.RepairException;
import com.technico.technicoproject.model.Repair;
import com.technico.technicoproject.service.RepairService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("repair/")

public class RepairController {
    private RepairService repairService;
    @PostMapping("/create/{propertyNumber}/{vatNumber}")
    public Repair create(@PathVariable("propertyNumber") String propertyNumber, @PathVariable("vatNumber") String vatNumber, @RequestBody Repair repair) throws RepairException {
        return repairService.createRepair(propertyNumber, vatNumber, repair);
    }

    @PutMapping("update/{caseId}")
    public Repair update(@PathVariable("caseId") Long caseId, @RequestBody Repair repair) {
        return repairService.updateRepair(caseId, repair);
    }

    @GetMapping("date/{dateFrom}/{dateTo}")
    public List<Repair> findByDates(@PathVariable("dateFrom") String dateFrom, @PathVariable("dateTo") String dateTo) {
        return repairService.readRepairsByDate(dateFrom, dateTo);
    }

    @GetMapping("vatnumber/{vatNumber}")
    public List<Repair> findRepairsByVatNumber(@PathVariable("vatNumber") String vatNumber) {
        return repairService.readRepairsByVatNumber(vatNumber);
    }

    @DeleteMapping("delete/{caseId}")
    public boolean delete(@PathVariable("caseId") Long caseId) {
        return repairService.deleteRepair(caseId);
    }

}


