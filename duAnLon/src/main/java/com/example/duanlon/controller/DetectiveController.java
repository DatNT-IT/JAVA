package com.example.duanlon.controller;

import com.example.duanlon.core.DataController;
import com.example.duanlon.model.Detective;
import com.example.duanlon.service.IDetectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playApi/detective")
public class DetectiveController {
    @Autowired
    private IDetectiveService detectiveService;

    @GetMapping("/")
    public Iterable<Detective> findAll() {
        return detectiveService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<DataController> findByIdDetective(@PathVariable Long id) {
        Detective detective = detectiveService.findById(id);
        if (detective != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Tim thay Detective co id = " + id).data(detective).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Khong tim thay Detective co id = " + id).data("").build());
        }
    }

    @PostMapping("/insert")
    ResponseEntity<DataController> insertDetective(@RequestBody Detective detective) {
        return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                .message("insert thanh cong Detective").data(detectiveService.save(detective)).build());
    }

    @PutMapping("/")
    ResponseEntity<DataController> updateDetective(@RequestBody Detective detective) {
        Detective detective1 = detectiveService.findById(detective.getId());
        if (detective1 != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Update Detective thanh cong").data(detectiveService.createDetective(detective1)).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Update Detective khong thanh cong").data("").build());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteDetective(@PathVariable Long id) {
        detectiveService.deleteById(id);

    }
}
