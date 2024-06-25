package com.qa.lbg.service;

import com.qa.lbg.domain.Cat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class CatServiceOld {

    private final List<Cat> cats = new ArrayList<>();

    public ResponseEntity<Cat> createCat(Cat newCat) {
        this.cats.add(newCat);
        Cat added = this.cats.get(this.cats.size() - 1);
        return new ResponseEntity<>(added, HttpStatus.CREATED);
    }

    public List<Cat> getAll() {
        return this.cats;
    }

    public ResponseEntity<?> getCat(Integer id) {
        if (id == null || id < 0 || id >= this.cats.size())
            return new ResponseEntity<>("No cat found with id: " + id, HttpStatus.NOT_FOUND);
        else return ResponseEntity.ok(this.cats.get(id));
    }


    public Cat updateCat(Integer id,
                         String name,
                         String colour,
                         Integer age,
                         String nature,
                         Integer lives) {

        Cat toUpdate = this.cats.get(id);

        if (name != null) toUpdate.setName(name);
        if (colour != null) toUpdate.setColour(colour);
        if (age != null) toUpdate.setAge(age);
        if (nature != null) toUpdate.setNature(nature);
        if (lives != null) toUpdate.setLives(lives);

        return toUpdate;
    }

    public ResponseEntity<?> removeCat(Integer id) {
        if (id == null || id < 0 || id >= this.cats.size())
            return new ResponseEntity<>("No cat found with id: " + id, HttpStatus.NOT_FOUND);
        else return ResponseEntity.ok(this.cats.remove(id.intValue()));
    }


}
