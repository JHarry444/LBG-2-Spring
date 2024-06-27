package com.qa.lbg.service;

import com.qa.lbg.domain.Cat;
import com.qa.lbg.domain.Toy;
import com.qa.lbg.dtos.CatDto;
import com.qa.lbg.dtos.ToyDto;
import com.qa.lbg.repos.ToyRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToyService {

    private ToyRepo repo;

    public ToyService(ToyRepo repo) {
        this.repo = repo;
    }


    public ResponseEntity<ToyDto> createToy(Toy newToy) {
        Toy created = this.repo.save(newToy);

        return new ResponseEntity<>(new ToyDto(created), HttpStatus.CREATED);
    }

    public List<ToyDto> getAll() {
        List<ToyDto> dtos = new ArrayList<>();
        List<Toy> found =  this.repo.findAll();
        for (Toy toy : found) {
            dtos.add(new ToyDto(toy));
        }
        return dtos;
    }

    public ResponseEntity<?> getToy(Integer id) {
        if (!this.repo.existsById(id))
            return new ResponseEntity<>("No toy found with id: " + id, HttpStatus.NOT_FOUND);

        Toy found = this.repo.findById(id).get();
        // missing not found logic
        return ResponseEntity.ok(new ToyDto(found));
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

        return ResponseEntity.ok(new ToyDto(updated));
    }

    public ResponseEntity<?> removeToy(Integer id) {
        if (!this.repo.existsById(id))
            return new ResponseEntity<>("No toy found with id: " + id, HttpStatus.NOT_FOUND);

        Toy found = this.repo.findById(id).get();

        this.repo.deleteById(id);

        return ResponseEntity.ok(new ToyDto(found));
    }
}
