package com.qa.lbg.domain;

import jakarta.persistence.*;

@Entity
public class Toy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;


    private boolean makeNoise;

    private boolean catnip;

    // Unidirectional relationship
    @ManyToOne
    private Cat cat;

    public Toy() {
    }

    public Toy(Integer id, String type, boolean makeNoise, boolean catnip) {
        this.id = id;
        this.type = type;
        this.makeNoise = makeNoise;
        this.catnip = catnip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMakeNoise() {
        return makeNoise;
    }

    public void setMakeNoise(boolean makeNoise) {
        this.makeNoise = makeNoise;
    }

    public boolean isCatnip() {
        return catnip;
    }

    public void setCatnip(boolean catnip) {
        this.catnip = catnip;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}
