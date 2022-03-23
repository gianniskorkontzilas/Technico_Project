package com.technico.technicoproject.service;

import com.technico.technicoproject.dto.ResponseResult;
import com.technico.technicoproject.dto.ResponseStatus;
import com.technico.technicoproject.model.Owner;
import com.technico.technicoproject.repository.OwnerRepository;
import com.technico.technicoproject.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * The Class OwnerServiceImpl is a set of Methods that concern Owner
 */
@AllArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {
    private OwnerRepository ownerRepository;
    private PropertyRepository propertyRepository;

    /**
     * This Method checks before Create an Owner these validations : check for non-duplicate of vatNumber and email
     * * Checks for vatNumber and phoneNumber number of digits
     *
     * @param owner
     * @return ResponseResult<Owner>
     */
    @Override
    public ResponseResult<Owner> createOwner(Owner owner) {

        boolean exists = ownerRepository.existsOwnersByVatNumber(owner.getVatNumber());
        boolean sameEmail = ownerRepository.existsByEmail(owner.getEmail());
        String regexPattern = "^(.+)@(\\S+)$";

        if (exists) {
            return new ResponseResult(null, ResponseStatus.OWNER_NOT_CREATED, "Owner with same VAT already exists");
        }
        if (sameEmail) {
            return new ResponseResult(null, ResponseStatus.OWNER_NOT_CREATED, "Owner with same email already exists");
        }
        if (owner.getVatNumber().length() != 9) {
            return new ResponseResult(null, ResponseStatus.OWNER_NOT_CREATED, "VAT number must be equal to 9 digits");
        }
        if (!Pattern.compile(regexPattern).matcher(owner.getEmail()).matches()) {
            return new ResponseResult(null, ResponseStatus.OWNER_NOT_CREATED, "Invalid email format");
        }
        if (owner.getPhoneNumber().length() != 10) {
            return new ResponseResult(null, ResponseStatus.OWNER_NOT_CREATED, "Invalid phone number length");
        }
        try {
            ownerRepository.save(owner);
        } catch (Exception e) {
            return new ResponseResult(null, ResponseStatus.OWNER_NOT_CREATED, "Owner creation failed due to: " + e);
        }

        return new ResponseResult(owner, ResponseStatus.SUCCESS, "Owner created successfully");
    }

    /**
     * This method Return a list with all Owners
     *
     * @return ResponseResult<List < Owner>>
     */
    @Override
    public ResponseResult<List<Owner>> readOwners() {
        return new ResponseResult(ownerRepository.findAll(), ResponseStatus.SUCCESS, "Returned list of owners successfully");
    }

    /**
     * This method Return  the Owner with this vatNumber if exists
     * @param vatNumber
     * @return ResponseResult<Owner>
     */
    @Override
    public ResponseResult<Owner> readOwnerByVatNumber(String vatNumber) {
        Owner owner = ownerRepository.findOwnerByVatNumber(vatNumber);
        if (owner == null) {
            return new ResponseResult(null, ResponseStatus.OWNER_NOT_FOUND, "Cannot find owner with VAT: " + vatNumber);
        } else {
            return new ResponseResult(owner, ResponseStatus.SUCCESS, "Owner found successfully");
        }

    }

    /**
     * This method Return  the Owner with the email if exists
     * @param email
     * @return ResponseResult<Owner>
     */
    @Override
    public ResponseResult<Owner> readOwnerByEmail(String email) {
        Owner owner = ownerRepository.findOwnerByEmail(email);
        if (owner == null) {
            return new ResponseResult(null, ResponseStatus.OWNER_NOT_FOUND, "Cannot find owner with " + email + "as email.");
        } else {
            return new ResponseResult(owner, ResponseStatus.SUCCESS, "Owner found successfully");
        }

    }

    /**
     * This method get  the Owner with the vatNumber if exists and then Update the Owner
     * @param vatNumber
     * @param owner
     * @return ResponseResult<Owner>
     */
    @Override
    public ResponseResult<Owner> updateOwner(String vatNumber, Owner owner) {

        Optional<Owner> ownerDb = ownerRepository.findById(vatNumber);
        if (ownerDb.isPresent()) {
            ownerDb.get().setAddress(owner.getAddress());
            ownerDb.get().setEmail(owner.getEmail());
            ownerDb.get().setFirstName(owner.getFirstName());
            ownerDb.get().setLastName(owner.getLastName());
            ownerDb.get().setPassword(owner.getPassword());
            ownerDb.get().setPhoneNumber(owner.getPhoneNumber());
            ownerDb.get().setVatNumber(owner.getVatNumber());
            ownerDb.get().setUsername(owner.getUsername());
            ownerRepository.save(ownerDb.get());
            return new ResponseResult(ownerDb, ResponseStatus.SUCCESS, "Successfully updated owner details.");
        } else {
            return new ResponseResult(null, ResponseStatus.OWNER_NOT_UPDATED, "Update failed because owner with VAT: " + vatNumber + " doesn't exist");
        }

    }

    /**
     * This method get  the Owner with the vatNumber if exists and then Delete the Owner if there is no any Property with this vatNumber
     * @param vatNumber
     * @return ResponseResult<Boolean>
     */
    @Override
    public ResponseResult<Boolean> deleteOwner(String vatNumber) {
        if (ownerRepository.findById(vatNumber) == null) {
            return new ResponseResult(false, ResponseStatus.OWNER_NOT_DELETED, "Delete operation failed because owner with VAT: " + vatNumber + " doesn't exist");
        }
        if (propertyRepository.findPropertiesByOwner(ownerRepository.findOwnerByVatNumber(vatNumber)).size() != 0) {
            return new ResponseResult(false, ResponseStatus.OWNER_NOT_DELETED, "Delete operation failed because owner has properties");
        }

        ownerRepository.deleteById(vatNumber);
        return new ResponseResult(true, ResponseStatus.SUCCESS, "Owner details deleted successfully");

    }

    /**
     * This method delete all Owners
     *
     * @return ResponseResult<Boolean>
     */
    @Override
    public ResponseResult<Boolean> deleteAllOwners() {
        ownerRepository.deleteAll();
        return new ResponseResult(true, ResponseStatus.SUCCESS, "All owners successfully deleted");
    }


}