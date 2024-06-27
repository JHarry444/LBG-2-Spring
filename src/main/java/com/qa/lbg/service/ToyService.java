package com.qa.lbg.service;

import com.qa.lbg.domain.Toy;
import com.qa.lbg.repos.ToyRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToyService {

    private ToyRepo repo;

    public ToyService(ToyRepo repo) {
        this.repo = repo;
    }


    public ResponseEntity<Toy> createToy(Toy newToy) {
        Toy created = this.repo.save(newToy);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    public List<Toy> getAll() {
        return this.repo.findAll();
    }

    public ResponseEntity<?> getToy(Integer id) {
        if (!this.repo.existsById(id))
            return new ResponseEntity<>("No toy found with id: " + id, HttpStatus.NOT_FOUND);

        Toy found = this.repo.findById(id).get();
        // missing not found logic
        return ResponseEntity.ok(found);
    }

    public ResponseEntity<?> updateToy(Integer id,
                                       String type,
                                       Boolean makeNoise,
                                       Boolean catnip) {

        Optional<Toy> found = this.repo.findById(id);

        if (found.isEmpty()) return new ResponseEntity<>("No toy found with id: " + id, HttpStatus.NOT_FOUND);

        Toy toUpdate = found.get();

        if (type != null) toUpdate.setType(type);
        if (makeNoise != null) toUpdate.setMakeNoise(makeNoise);
        if (catnip != null) toUpdate.setCatnip(catnip);


        Toy updated = this.repo.save(toUpdate);

        return ResponseEntity.ok(updated);
    }

    public ResponseEntity<?> removeToy(Integer id) {
        if (!this.repo.existsById(id))
            return new ResponseEntity<>("No toy found with id: " + id, HttpStatus.NOT_FOUND);

        Toy found = this.repo.findById(id).get();

        this.repo.deleteById(id);

        return ResponseEntity.ok(found);
    }
}
