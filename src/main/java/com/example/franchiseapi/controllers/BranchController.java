package com.example.franchiseapi.controllers;

import com.example.franchiseapi.models.Branch;
import com.example.franchiseapi.models.Franchise;
import com.example.franchiseapi.models.ProductStockResponse;
import com.example.franchiseapi.repository.BranchRepository;
import com.example.franchiseapi.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    @PostMapping
    public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
        if (branch.getFranchise() != null && branch.getFranchise().getId() != null) {
            Franchise franchise = franchiseRepository.findById(branch.getFranchise().getId())
                    .orElse(null);
            if (franchise == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Franchise not found
            }
            branch.setFranchise(franchise); // Set the franchise to the branch
        }

        Branch savedBranch = branchRepository.save(branch);
        return new ResponseEntity<>(savedBranch, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable Integer id, @RequestBody Branch branchDetails) {
        Branch branch = branchRepository.findById(id).orElse(null);
        if (branch == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        branch.setName(branchDetails.getName());
        return new ResponseEntity<>(branchRepository.save(branch), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Integer id) {
        branchRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/franchise/{franchiseId}/top-stock-product")
    public ResponseEntity<List<ProductStockResponse>> getTopStockProductByFranchise(@PathVariable Integer franchiseId) {
        List<ProductStockResponse> response = branchRepository.findTopStockProductsByFranchise(franchiseId);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(response);
    }

}
