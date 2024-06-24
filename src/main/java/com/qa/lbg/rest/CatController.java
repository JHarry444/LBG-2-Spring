package com.qa.lbg.rest;

import com.qa.lbg.domain.Cat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// controllers handle requests
@RestController
public class CatController {

    private List<Cat> cats = new ArrayList<>();

    // this method will be called when a GET request is sent to /hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping("/cat/new")
    public Cat createCat(@RequestBody Cat newCat) {
        this.cats.add(newCat);
        return this.cats.get(this.cats.size() - 1);
    }

    @GetMapping("/cat/all")
    public List<Cat> getAll() {
        return this.cats;
    }

    @GetMapping("/cat/{id}")
    public Cat getCat(@PathVariable Integer id) {
        return this.cats.get(id);
    }


    @PatchMapping("/cat/{id}")
    public Cat updateCat(@PathVariable(name = "id") Integer id,
                         @RequestParam(name = "name", required = false) String name,
                         @RequestParam(name = "colour", required = false) String colour,
                         @RequestParam(name = "age", required = false) Integer age,
                         @RequestParam(name = "nature", required = false) String nature,
                         @RequestParam(name = "lives", required = false) Integer lives) {

        Cat toUpdate = this.cats.get(id);

        if (name != null) toUpdate.setName(name);
        if (colour != null) toUpdate.setColour(colour);
        if (age != null) toUpdate.setAge(age);
        if (nature != null) toUpdate.setNature(nature);
        if (lives != null) toUpdate.setLives(lives);

        return toUpdate;
    }

    @DeleteMapping("/cat/{id}")
    public Cat removeCat(@PathVariable Integer id) {
        Cat toRemove = this.cats.get(id);
        return this.cats.remove(id.intValue());
    }
}
