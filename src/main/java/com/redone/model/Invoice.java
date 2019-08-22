package com.redone.model;

import com.redone.IInvoiceCalculator;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Invoice implements IInvoiceCalculator{

    private List<Product> products = new ArrayList<>();


    public Invoice add(Product product){
        products.add(requireNonNull(product));
        return this;
    }

    @Override
    public List<Double> getTotal() {

        double totalTax = products.stream().mapToDouble(this::calculateTax).sum();
        double totalHT = products.stream().mapToDouble(p-> p.getPrice() * p.getQuantity()).sum();

        List<Double> pricesAndTaxes = new ArrayList<Double>();
        pricesAndTaxes.add(IInvoiceCalculator.round(totalHT + totalTax));
        pricesAndTaxes.add(IInvoiceCalculator.round(totalTax));
        return pricesAndTaxes;

    }

    @Override
    public double calculateTax(Product p) {
        double tax = p.getQuantity() * IInvoiceCalculator.roundTax(p.getPrice()*p.getTax()/100);
        p.setPriceTTC(IInvoiceCalculator.round(p.getPrice() + tax));
        return tax;
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();
        List<Double> pricesAndTaxes = getTotal();

        products.forEach(p-> sb.append(p.toString()).append("\n"));

        sb.append("Montant des taxes : ").append(pricesAndTaxes.get(1)).append("\n");
        sb.append("Total : ").append(pricesAndTaxes.get(0));

        return new String(sb);
    }

}
