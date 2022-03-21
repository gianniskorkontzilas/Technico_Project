package com.technico.technicoproject.service;

import com.technico.technicoproject.exception.RepairException;
import com.technico.technicoproject.model.Repair;
import com.technico.technicoproject.repository.OwnerRepository;
import com.technico.technicoproject.repository.PropertyRepository;
import com.technico.technicoproject.repository.RepairRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RepairServiceImpl implements RepairService {
    private RepairRepository repairRepository;
    private PropertyRepository propertyRepository;
    private OwnerRepository ownerRepository;

    @Override
    public Repair createRepair(String propertyNumber, String vatNumber, Repair repair) throws RepairException {

        if (propertyRepository.findById(propertyNumber) != null && ownerRepository.findOwnerByVatNumber(vatNumber) != null) {
            repair.setProperty(propertyRepository.findPropertyByAddress(propertyNumber));
            repair.setOwner(ownerRepository.findOwnerByVatNumber(vatNumber));
            repair.setProperty(propertyRepository.findById(propertyNumber).get());
            repair.setDateOfRegistration(LocalDate.now());
            return repairRepository.save(repair);
        } else {
            throw new RepairException("Invalid Data for  Creation of Repair");
        }
    }

    @Override
    public Repair updateRepair(Long caseId, Repair repair) {
        Optional<Repair> repairDb = repairRepository.findById(caseId);
        if (repairDb.isEmpty())
            return null;
        repairDb.get().setRepairStatus(repair.getRepairStatus());
        repairDb.get().setTypeOfRepair(repair.getTypeOfRepair());
        repairDb.get().setCost(repair.getCost());
        repairDb.get().setDescription(repair.getDescription());
        return repairRepository.save(repairDb.get());
    }

    @Override
    public List<Repair> readRepairsByDate(String fromDate, String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate fromLocalDate = LocalDate.parse(fromDate, formatter);
        LocalDate toLocalDate = LocalDate.parse(toDate, formatter);


        List<Repair> repairList = repairRepository.findAll();
        List<Repair> requestedRepairList = new ArrayList<>();

        for (Repair repair : repairList) {
            if (repair.getDateOfRegistration().isAfter(fromLocalDate) && repair.getDateOfRegistration().isBefore(toLocalDate)) {
                requestedRepairList.add(repair);
            }
        }
        return requestedRepairList;
    }

    @Override
    public List<Repair> readRepairsByVatNumber(String vatNumber) {
        return repairRepository.findRepairsByOwnerVatNumber(vatNumber);

    }


    @Override
    public boolean deleteRepair(Long caseId) {
        Optional<Repair> repairDto = repairRepository.findById(caseId);
        if (repairDto.isPresent()) {
            repairRepository.delete(repairDto.get());
            return true;
        } else return false;
    }
}