package com.example.duanlon.controller;

import com.example.duanlon.core.DataController;
import com.example.duanlon.model.CriminalCase;
import com.example.duanlon.service.ICriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playApi/criminalCase")
public class CriminalCaseController {
    @Autowired
    private ICriminalCaseService iCriminalCaseService;


    @GetMapping("/")
    public Iterable<CriminalCase> findAll() {
        return iCriminalCaseService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<DataController> findByIdCriminalCase(@PathVariable Long id) {
        CriminalCase criminalCase = iCriminalCaseService.findById(id);
        if (criminalCase != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Tim thay CriminalCase co id = " + id).data(criminalCase).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Khong tim thay CriminalCase co id = " + id).data("").build());
        }
    }

    @PostMapping("/insert")
    ResponseEntity<DataController> insertCriminalCase(@RequestBody CriminalCase criminalCase) {
        return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                .message("insert thanh cong CriminalCase").data(iCriminalCaseService.save(criminalCase)).build());
    }

    @PutMapping("/")
    ResponseEntity<DataController> updateCriminalCase(@RequestBody CriminalCase criminalCase) {
        CriminalCase criminalCase1 = iCriminalCaseService.findById(criminalCase.getId());
        if (criminalCase1 != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Update CriminalCase thanh cong").data(iCriminalCaseService.createCriminalCase(criminalCase1)).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Update CriminalCase khong thanh cong").data("").build());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCriminalCase(@PathVariable Long id) {
        iCriminalCaseService.deleteById(id);

    }

}
