package com.qa.lbg.dtos;

import com.qa.lbg.domain.Cat;
import com.qa.lbg.domain.Toy;

import java.util.ArrayList;
import java.util.List;

public class CatDto {

    private Integer id;

    private String name;

    private String colour;

    private int age;

    private String nature;

    private int lives;

    private List<ToyDto> toys = new ArrayList<>();

    // REQUIRED
    public CatDto() {
        super();
    }

    public CatDto(Cat cat) {
        this.id = cat.getId();
        this.name = cat.getName();
        this.colour = cat.getColour();
        this.age = cat.getAge();
        this.nature = cat.getNature();
        this.lives = cat.getLives();
        if (cat.getToys() != null) {
            for (Toy toy : cat.getToys()) {
                this.toys.add(new ToyDto(toy));
            }
        }

    }

    public CatDto(Integer id, String name, String colour, int age, String nature, int lives) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        this.age = age;
        this.nature = nature;
        this.lives = lives;
    }

    // REQUIRED
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public List<ToyDto> getToys() {
        return toys;
    }

    public void setToys(List<ToyDto> toys) {
        this.toys = toys;
    }
}
