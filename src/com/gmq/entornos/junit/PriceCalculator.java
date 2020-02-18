package com.gmq.entornos.junit;

import static com.gmq.entornos.junit.PriceCalculator.Products.*;

public class PriceCalculator {

    public Integer sumProductsPrice(Integer[] productPrices) {
            Integer result = 0;
            for (Integer productPrice : productPrices) {
                result += productPrice;
            }
            return result;
    }

    public double calculatePrice(String productName, Integer numberOfProducts) {
    	try {
    		Products product = Products.valueOf(productName);

            double tax = calculateTax(product);
            double co2Tax = 0;
            double trushTax = 0;
            double price = 0; 

            if(isATransport(product)) {
                if(isAnAirTransport(product)) {
                    co2Tax = 20; 
                } else {
                    co2Tax = 10;
                }
            }

            if(isAHome(product)) {
                trushTax = 3;
            }

            if (tax >= 0) {
                price = (((tax + co2Tax + trushTax) / 100) + 1) * product.getUnitPrice();
            }
            return price*numberOfProducts;
    	} catch(IllegalArgumentException e) {
    		return 0;
    	}
        
    }

    private boolean isAHome(Products product) {
        return HOUSE == product ||  APPARTMENT == product;
    }

    private boolean isAnAirTransport(Products product) {
        return PLANE == product ||  HELICOPTER == product;
    }

    private boolean isAnEathTransport(Products product) {
        return CAR == product || TRUCK == product;
    }

    private boolean isATransport(Products product) {
        return isAnEathTransport(product) || isAnAirTransport(product);
    }

    private int calculateTax(Products product) {
        int tax;
        switch (product) {
            case CAR:
            case PLANE:
            case TRUCK:
            case HELICOPTER:
                tax = 21;
                break;
            case HOUSE:
            case APPARTMENT:
                tax = 10;
                break;
            case FOOD:
                tax = 4;
                break;
            default:
                return -1;
        }
        return tax;
    }

    enum Products {
        CAR("car", 15000),
        HOUSE("house", 350000),
        APPARTMENT("appartment", 200000),
        TRUCK("truck", 60000),
        FOOD("food", 50),
        PLANE("plane", 3500000),
        HELICOPTER("helicopter", 1400000),
        BIKE("bike", 1000);

        String name;

        int unitPrice;

        Products(String name, int unitPrice) {
            this.name = name;
            this.unitPrice = unitPrice;
        }

        int getUnitPrice() {
            return this.unitPrice;
        }
    }

}
