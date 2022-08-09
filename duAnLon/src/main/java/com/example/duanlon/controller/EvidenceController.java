package com.example.duanlon.controller;

import com.example.duanlon.core.DataController;
import com.example.duanlon.model.Evidence;
import com.example.duanlon.service.IEvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playApi/evidence")
public class EvidenceController {
    @Autowired
    private IEvidenceService evidenceService;

    @GetMapping("/")
    public Iterable<Evidence> findAll() {
        return evidenceService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<DataController> findByIdEvidence(@PathVariable Long id) {
        Evidence evidence = evidenceService.findById(id);
        if (evidence != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Tim thay Evidence co id = " + id).data(evidence).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Khong tim thay Evidence co id = " + id).data("").build());
        }
    }

    @PostMapping("/insert")
    ResponseEntity<DataController> insertEvidence(@RequestBody Evidence evidence) {
        return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                .message("insert thanh cong Evidence").data(evidenceService.save(evidence)).build());
    }

    @PutMapping("/")
    ResponseEntity<DataController> updateEvidence(@RequestBody Evidence evidence) {
        Evidence evidence1 = evidenceService.findById(evidence.getId());
        if (evidence1 != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Update Evidence thanh cong").data(evidenceService.createEvidence(evidence1)).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Update Evidence khong thanh cong").data("").build());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEvidence(@PathVariable Long id) {
        evidenceService.deleteById(id);

    }
}
