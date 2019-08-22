package com.redone;


import com.redone.model.Category;
import com.redone.model.Invoice;
import com.redone.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {

    private Product p1;
    private Product p2;
    private Product p3;
    private Product p4;

    private Invoice invoice = null;


    @Test()
    public void case1(){
        try {

            p1 = new Product("livre",12.49,1, Category.BOOKS,false);
            p2 = new Product("CD musical",14.99,1, Category.OTHERS,false);
            p3 = new Product("barre de chocolat",0.85,1, Category.FOOD,false);

            invoice = new Invoice();

            invoice.add(p1);
            invoice.add(p2);
            invoice.add(p3);

            String expected = "1 livre : 12.49\n" +
                    "1 CD musical : 16.49\n" +
                    "1 barre de chocolat : 0.85\n" +
                    "Montant des taxes : 1.5\n" +
                    "Total : 29.83";

            Assertions.assertEquals(expected,invoice.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void case2(){
        try {

            p1 = new Product("boite de chocolats",10.00,1, Category.FOOD,true);
            p2 = new Product("flacon de parfum",47.50,1, Category.OTHERS,true);

            invoice = new Invoice();

            invoice.add(p1);
            invoice.add(p2);


            String expected = "1 boite de chocolats importÈ(e) : 10.5\n" +
                    "1 flacon de parfum importÈ(e) : 54.65\n" +
                    "Montant des taxes : 7.65\n" +
                    "Total : 65.15";

            Assertions.assertEquals(expected,invoice.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test()
    public void case3(){
        try {

            p1 = new Product("flacon de parfum",27.99,1, Category.OTHERS,true);
            p2 = new Product("flacon de parfum",18.99,1, Category.OTHERS,false);
            p3 = new Product("boîte de pilules contre la migraine",9.75,1, Category.MEDICS,false);
            p4 = new Product("boîte de chocolats",11.25,1, Category.FOOD,true);


            invoice = new Invoice();

            invoice.add(p1);
            invoice.add(p2);
            invoice.add(p3);
            invoice.add(p4);

            String expected = "1 flacon de parfum importÈ(e) : 32.19\n" +
                    "1 flacon de parfum : 20.89\n" +
                    "1 boîte de pilules contre la migraine : 9.75\n" +
                    "1 boîte de chocolats importÈ(e) : 11.85\n" +
                    "Montant des taxes : 6.7\n" +
                    "Total : 74.68";

            Assertions.assertEquals(expected,invoice.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void caseRound(){

        double value1 = 0.99;
        double value2 = 1.00;
        double value3 = 1.01;
        double value4 = 1.02;

        Assertions.assertEquals(1.00, IInvoiceCalculator.roundTax(value1));
        Assertions.assertEquals(1.00, IInvoiceCalculator.roundTax(value2));
        Assertions.assertEquals(1.05, IInvoiceCalculator.roundTax(value3));
        Assertions.assertEquals(1.05, IInvoiceCalculator.roundTax(value4));

    }
}