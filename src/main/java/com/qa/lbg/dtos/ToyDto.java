package com.qa.lbg.dtos;

import com.qa.lbg.domain.Cat;
import com.qa.lbg.domain.Toy;

public class ToyDto {

    private Integer id;

    private String type;

    private boolean makeNoise;

    private boolean catnip;
 // no Cat so there's no infinite loop
    public ToyDto() {
    }

    public ToyDto(Toy toy) {
        this.id = toy.getId();
        this.type = toy.getType();
        this.makeNoise = toy.isMakeNoise();
        this.catnip = toy.isCatnip();
    }

    public ToyDto(Integer id, String type, boolean makeNoise, boolean catnip) {
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

}