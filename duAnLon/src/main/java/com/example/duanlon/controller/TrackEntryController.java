package com.example.duanlon.controller;

import com.example.duanlon.core.DataController;
import com.example.duanlon.model.TrackEntry;
import com.example.duanlon.service.ITrackEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playApi/trackEntry")
public class TrackEntryController {
    @Autowired
    private ITrackEntryService trackEntryService;

    @GetMapping("/")
    public Iterable<TrackEntry> findAll() {
        return trackEntryService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<DataController> findByIdTrackEntry(@PathVariable Long id) {
        TrackEntry trackEntry = trackEntryService.findById(id);
        if (trackEntry != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Tim thay TrackEntry co id = " + id).data(trackEntry).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Khong tim thay TrackEntry co id = " + id).data("").build());
        }
    }

    @PostMapping("/insert")
    ResponseEntity<DataController> insertTrackEntry(@RequestBody TrackEntry trackEntry) {
        return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                .message("insert thanh cong TrackEntry").data(trackEntryService.save(trackEntry)).build());
    }

    @PutMapping("/")
    ResponseEntity<DataController> updateTrackEntry(@RequestBody TrackEntry trackEntry) {
        TrackEntry trackEntry1 = trackEntryService.findById(trackEntry.getId());
        if (trackEntry1 != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Update TrackEntry thanh cong").data(trackEntryService.createTrackEntry(trackEntry1)).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Update TrackEntry khong thanh cong").data("").build());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTrackEntry(@PathVariable Long id) {
        trackEntryService.deleteById(id);

    }
}
