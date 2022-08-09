package com.example.duanlon.controller;

import com.example.duanlon.core.DataController;
import com.example.duanlon.model.Person;
import com.example.duanlon.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playApi/person")
public class PersonController {
    @Autowired
    private IPersonService personService;

    @GetMapping("/")
    public Iterable<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<DataController> findByIdPerson(@PathVariable Long id) {
        Person person = personService.findById(id);
        if (person != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Tim thay Person co id = " + id).data(person).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Khong tim thay Person co id = " + id).data("").build());
        }
    }

    @PostMapping("/insert")
    ResponseEntity<DataController> insertPerson(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                .message("insert thanh cong Person").data(personService.save(person)).build());
    }

    @PutMapping("/")
    ResponseEntity<DataController> updatePerson(@RequestBody Person person) {
        Person person1 = personService.findById(person.getId());
        if (person1 != null) {
            return ResponseEntity.status(HttpStatus.OK).body(DataController.builder().status("OK")
                    .message("Update Person thanh cong").data(personService.createPerson(person1)).build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DataController.builder().status("NO")
                    .message("Update Person khong thanh cong").data("").build());
        }
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deleteById(id);

    }
}
