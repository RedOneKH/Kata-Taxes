package com.redone.model;


import lombok.Getter;

@Getter
public enum Category {
    BOOKS(0),
    FOOD(0),
    MEDICS(0),
    OTHERS(10);

    Category(int tax){
        this.tax=tax;
    }
    protected int tax;

}
