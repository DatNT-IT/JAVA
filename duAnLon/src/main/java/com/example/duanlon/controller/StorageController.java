package com.example.duanlon.controller;

import com.example.duanlon.core.DataController;
import com.example.duanlon.model.Storage;
import com.example.duanlon.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playApi/storage")
public class StorageController {
    @Autowired
    private IStorageService storageService;

    @GetMapping("/")
    public Iterable<Storage> findAll() {
        return storageService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<DataController> findByIdStorage(@PathVariable Long id) {
        Storage storage = storageService.findById(id);
        if (storage != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Tim thay Storage co id = " + id).data(storage).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Khong tim thay Storage co id = " + id).data("").build());
        }
    }

    @PostMapping("/insert")
    ResponseEntity<DataController> insertStorage(@RequestBody Storage storage) {
        return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                .message("insert thanh cong Storage").data(storageService.save(storage)).build());
    }

    @PutMapping("/")
    ResponseEntity<DataController> updateStorage(@RequestBody Storage storage) {
        Storage storage1 = storageService.findById(storage.getId());
        if (storage1 != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Update Storage thanh cong").data(storageService.createStorage(storage1)).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Update Storage khong thanh cong").data("").build());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStorage(@PathVariable Long id) {
        storageService.deleteById(id);

    }
}
