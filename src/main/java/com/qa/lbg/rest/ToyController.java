package com.qa.lbg.rest;

import com.qa.lbg.domain.Toy;
import com.qa.lbg.service.ToyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controllers handle requests
@RestController
public class ToyController {

    private ToyService service;

    public ToyController(ToyService service) {
        this.service = service;
    }

    @PostMapping("/toy/new")
    public ResponseEntity<Toy> createToy(@RequestBody Toy newToy) {
        return this.service.createToy(newToy);
    }

    @GetMapping("/toy/all")
    public List<Toy> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/toy/{id}")
    public ResponseEntity<?> getToy(@PathVariable Integer id) {
        return this.service.getToy(id);
    }


    @PatchMapping("/toy/{id}")
    public ResponseEntity<?> updateToy(@PathVariable(name = "id") Integer id,
                         @RequestParam(name = "type", required = false) String type,
                         @RequestParam(name = "makeNoise", required = false) Boolean makeNoise,
                         @RequestParam(name = "catnip", required = false) Boolean catnip) {
    return this.service.updateToy(id, type, makeNoise, catnip);
    }

    @DeleteMapping("/toy/{id}")
    public ResponseEntity<?> removeToy(@PathVariable Integer id) {
        return this.service.removeToy(id);
    }
}
