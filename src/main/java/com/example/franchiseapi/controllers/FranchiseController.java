package com.example.franchiseapi.controllers;

import com.example.franchiseapi.models.Franchise;
import com.example.franchiseapi.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franchises")
public class FranchiseController {
    
    @Autowired
    private FranchiseRepository franchiseRepository;

    @PostMapping
    public ResponseEntity<Franchise> createFranchise(@RequestBody Franchise franchise) {
        Franchise savedFranchise = franchiseRepository.save(franchise);
        return new ResponseEntity<>(savedFranchise, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Integer id, @RequestBody Franchise franchiseDetails) {
        Franchise franchise = franchiseRepository.findById(id).orElse(null);
        if (franchise == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        franchise.setName(franchiseDetails.getName());
        return new ResponseEntity<>(franchiseRepository.save(franchise), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFranchise(@PathVariable Integer id) {
        franchiseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}