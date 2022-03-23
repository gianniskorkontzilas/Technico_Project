package com.technico.technicoproject.controller;

import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.model.Owner;
import com.technico.technicoproject.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a Rest Controller which call The Owner Service (Dependency Injection)
 */
@RestController
@AllArgsConstructor
@RequestMapping("/owner")
public class OwnerController {
    private OwnerService ownerService;

    /**
     * This method call a method of OwnerService to Create an Owner
     *
     * @param owner
     * @return ResponseResult<Owner>
     * @throws Exception
     */
    @PostMapping("/create")
    public ResponseResult<Owner> create(@RequestBody Owner owner) throws Exception {
        return ownerService.createOwner(owner);
    }

    /**
     * This method call a method of OwnerService to Get all Owners
     *
     * @return ResponseResult<Owner>
     */
    @GetMapping("/owners")
    public ResponseResult<List<Owner>> findAllOwners() {
        return ownerService.readOwners();
    }

    /**
     * This method call a method of OwnerService to Get  the Owner with this vatNumber
     *
     * @param vatNumber
     * @return ResponseResult<Owner>
     */
    @GetMapping("/{vatNumber}")
    public ResponseResult<Owner> findByVat(@PathVariable("vatNumber") String vatNumber) {
        return ownerService.readOwnerByVatNumber(vatNumber);
    }

    /**
     * This method call a method of OwnerService to Get  the Owner with this email
     *
     * @param ownerEmail
     * @return ResponseResult<Owner>
     */
    @GetMapping("/email/{ownerEmail}")
    public ResponseResult<Owner> findByEmail(@PathVariable("ownerEmail") String ownerEmail) {
        return ownerService.readOwnerByEmail(ownerEmail);
    }

    /**
     * This method call a method of OwnerService to Update  the Owner with this vatNumber
     *
     * @param vatNumber
     * @return ResponseResult<Owner>
     */
    @PutMapping("/{vatNumber}")
    public ResponseResult<Owner> update(@PathVariable("vatNumber") String vatNumber, @RequestBody Owner owner) {
        return ownerService.updateOwner(vatNumber, owner);
    }

    /**
     * This method call a method of OwnerService to Delete  the Owner with this vatNumber
     *
     * @param vatNumber
     * @return ResponseResult<Owner>
     */
    @DeleteMapping("/{vatNumber}")
    public ResponseResult<Boolean> delete(@PathVariable("vatNumber") String vatNumber) {
        return ownerService.deleteOwner(vatNumber);
    }

    /**
     * This method call a method of OwnerService to Delete all Owners
     *
     * @return ResponseResult<Owner>
     */
    @DeleteMapping("")
    public ResponseResult<Boolean> delete() {
        return ownerService.deleteAllOwners();
    }
}

