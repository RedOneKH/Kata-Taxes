package com.redone;

import com.redone.model.Product;

import java.util.List;

public interface IInvoiceCalculator {


        public List<Double> getTotal();
        public double calculateTax(Product p);


        public static double roundTax(double tax){
                return round(Math.ceil(tax / 0.05f) * 0.05f);
        }
        public static double round(double round){
                return (double) Math.round(round * 100) / 100;
        }
}
