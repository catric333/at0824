package com.catric.at0824.tools.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.catric.at0824.tools.model.RentalAgreement;
import com.catric.at0824.tools.model.Tool;

@RestController
public class ProductController {
    
    // TODOL: change this to a backend call instead of saving off in memory to an arraylist
    private final Map<String, RentalAgreement> rentalAgreementMap = new HashMap<String, RentalAgreement>();

    @PostMapping("/createRentalAgreement")
    public ResponseEntity<RentalAgreement> createRentalAgreement(@RequestBody final Tool tool) {
        RentalAgreement newRentalAgreement = new RentalAgreement(tool);
        rentalAgreementMap.put(newRentalAgreement.getUUID(), newRentalAgreement);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(newRentalAgreement);
    }

    @GetMapping("/getAllRentalAgreements") 
    public List<RentalAgreement> getAllRentalAgreements() {
        return new ArrayList<>(rentalAgreementMap.values());
    }

    @GetMapping("/getRentalAgreement/{id}")
    public ResponseEntity<RentalAgreement> getRentalAgreementById(@PathVariable final String id) {
        if (!rentalAgreementMap.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(new RentalAgreement());
        }
        RentalAgreement target = rentalAgreementMap.get(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(target);
    }

    @PatchMapping("/updateRentalAgreement/{id}")
    public ResponseEntity<RentalAgreement> updateRentalAgreementById(@PathVariable final String id, @RequestBody final Tool tool) {
        RentalAgreement newRentalAgreement = new RentalAgreement(tool);
        newRentalAgreement.setUUID(id);
        if (!rentalAgreementMap.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(newRentalAgreement);
        }
        rentalAgreementMap.replace(id, newRentalAgreement);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(newRentalAgreement);
    }

    @DeleteMapping("/deleteRentalAgreement/{id}")
    public ResponseEntity<RentalAgreement> deleteRentalAgreementById(@PathVariable final String id) {
        if (!rentalAgreementMap.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(new RentalAgreement());
        }
        RentalAgreement target = rentalAgreementMap.remove(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(target);
    }

}
