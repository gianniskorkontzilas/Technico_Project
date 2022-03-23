package com.technico.technicoproject.service;

import com.technico.technicoproject.dto.RepairDto;
import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.dto.ResponseStatus;
import com.technico.technicoproject.model.Owner;
import com.technico.technicoproject.model.Property;
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
    public ResponseResult<Repair> createRepair(Repair repair)  {
        Property property = propertyRepository.findPropertyByOwnerAndAddress(ownerRepository.findOwnerByVatNumber(repair.getVatNumber()), repair.getAddress());
        if(property==null){
            return new ResponseResult(null, ResponseStatus.REPAIR_NOT_CREATED,"Repair creation failed, no property found on "+ repair.getAddress() + "  belonging to owner with VAT: " + repair.getVatNumber());
        }
        repair.setProperty(property);
        repair.setDateOfRegistration(LocalDate.now());
        repairRepository.save(repair);
        return new ResponseResult(repair,ResponseStatus.SUCCESS,"Repair created successfully");

    }

    @Override
    public ResponseResult<Repair> updateRepair(Long caseId, Repair repair) {
        Optional<Repair> repairDb = repairRepository.findById(caseId);
        Property property = propertyRepository.findPropertyByOwnerAndAddress(ownerRepository.findOwnerByVatNumber(repair.getVatNumber()), repair.getAddress());

        if (repairDb.isEmpty())
            return new ResponseResult(null,ResponseStatus.REPAIR_NOT_UPDATED,"Update failed, no repair with caseID:" +caseId +" found");

        if(property==null){
            return new ResponseResult(null, ResponseStatus.REPAIR_NOT_CREATED,"Repair creation failed, no property found on "+ repair.getAddress() + "  belonging to owner with VAT: " + repair.getVatNumber());
        }
        repairDb.get().setRepairStatus(repair.getRepairStatus());
        repairDb.get().setTypeOfRepair(repair.getTypeOfRepair());
        repairDb.get().setAddress(repair.getAddress());
        repairDb.get().setVatNumber(repair.getVatNumber());
        repairDb.get().setCost(repair.getCost());
        repairDb.get().setDescription(repair.getDescription());
        repairRepository.save(repairDb.get());
        return new ResponseResult(repairDb.get(),ResponseStatus.SUCCESS,"Repair details updated!");
    }


    @Override
    public ResponseResult<RepairDto> readRepair(Long caseId){
        Repair repair = repairRepository.findRepairByCaseId(caseId);
        if(repair==null){
            return new ResponseResult(null,ResponseStatus.REPAIR_NOT_FOUND,"No repair found with case ID "+caseId );
        }
        return new ResponseResult(this.returnEnteredValues(repair),ResponseStatus.SUCCESS,"Returned repair with case ID :" + caseId);
    }

    @Override
    public ResponseResult<List<RepairDto>> readRepairsByDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        List<Repair> repairsList = repairRepository.findRepairsByDateOfRegistration(date);
        return new ResponseResult(this.returnEnteredValuesList(repairsList),ResponseStatus.SUCCESS,"Returned list of repairs that begun on "+ date);
    }

    @Override
    public ResponseResult<List<RepairDto>> readRepairsByDate(String fromDateString, String toDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate fromDate = LocalDate.parse(fromDateString, formatter);
        LocalDate toDate = LocalDate.parse(toDateString,formatter);
        List<Repair> repairsList = repairRepository.findRepairsByDateOfRegistrationBetween(fromDate,toDate);
        return new ResponseResult(this.returnEnteredValuesList(repairsList),ResponseStatus.SUCCESS,"Returned list of repairs between "+ fromDate + " and "+toDate);
    }

    @Override
    public ResponseResult<List<RepairDto>> readRepairsByVatNumber(String vatNumber) {
        Owner owner = ownerRepository.findOwnerByVatNumber(vatNumber);
        if(owner==null){
            return new ResponseResult(null,ResponseStatus.REPAIR_NOT_FOUND,"Cannot search for repairs as no owner exists with this VAT "+ vatNumber);
        }
        List<Repair> repairsList = repairRepository.findRepairsByVatNumber(vatNumber);
        return new ResponseResult(this.returnEnteredValuesList(repairsList),ResponseStatus.SUCCESS,"Returned list of repairs to properties belonging to owner with VAT "+ vatNumber);

    }


    @Override
    public ResponseResult<Boolean> deleteRepair(Long caseId) {
        Optional<Repair> repair = repairRepository.findById(caseId);
        if(caseId==null){
            return new ResponseResult(false,ResponseStatus.REPAIR_NOT_DELETED,"Delete failed, no repair case with this case ID: "+ caseId);
        }
        return new ResponseResult(true,ResponseStatus.SUCCESS,"Deleted repair successfully");
    }


    public RepairDto returnEnteredValues(Repair repair){
        RepairDto repairDto = new RepairDto();
        repairDto.setCaseId(repair.getCaseId());
        repairDto.setDateOfRegistration(repair.getDateOfRegistration());
        repairDto.setDateOfCompletion(repair.getDateOfCompletion());
        repairDto.setAddress(repair.getProperty().getAddress());
        repairDto.setVatNumber(repair.getVatNumber());
        repairDto.setRepairStatus(repair.getRepairStatus());
        repairDto.setTypeOfRepair(repair.getTypeOfRepair());
        repairDto.setCost(repair.getCost());
        repairDto.setDescription(repair.getDescription());
        return repairDto;
    }

    public List<RepairDto> returnEnteredValuesList(List<Repair> repairs){
        List<RepairDto> repairDtos = new ArrayList<>();
        for (Repair repair : repairs){
            repairDtos.add(this.returnEnteredValues(repair));
        }
        return repairDtos;
    }


}