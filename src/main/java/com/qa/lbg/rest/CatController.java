package com.qa.lbg.rest;

import com.qa.lbg.domain.Cat;
import org.springframework.stereotype.Controller;
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

    int sum(int a, int b) {
        return a + b;
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
}
