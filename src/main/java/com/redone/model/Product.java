package com.redone.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Getter
public class Product {

    private String name ;
    private double price ;
    private int quantity;
    private Category category;
    private boolean isImported ;
    private @Setter double priceTTC;

    public Product(String name, double price, int quantity, Category category, boolean isImported) throws Exception{
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.category = category;
            this.isImported = isImported;
            validate();
    }

    private void validate() throws Exception {
        List<String> errors = new ArrayList<>();

        if (!(name != null && name.trim().length() > 0)) {
            errors.add("Name has no content.");
        }

        if (price <0) {
            errors.add("Price cannot be negative.");
        }
        if (quantity <=0) {
            errors.add("Quantity should be greater than 0.");
        }

        if (category == null) {
            errors.add("Category cannot be null.");
        }

        if (!errors.isEmpty()) {
            Exception ex = new Exception();
            for(String error: errors) {
                ex.addSuppressed(new Exception(error));
            }
            throw ex;
        }
    }

    public int getTax(){
        return isImported() ? (category.getTax() + 5) : category.getTax();
    }

    @Override
    public String toString() {
        return quantity+ " " + name + (isImported ? " importÈ(e)":"") +" : " + priceTTC ;
    }
}
