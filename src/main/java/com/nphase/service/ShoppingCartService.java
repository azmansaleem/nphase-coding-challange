package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import javax.naming.OperationNotSupportedException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ShoppingCartService {

    public BigDecimal calculateTotalPriceTask1(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal calculateTotalPriceTask2(ShoppingCart shoppingCart) {
        List<Product> productList = shoppingCart.getProducts();
        productList.forEach(product -> {
            if (product.getQuantity() > 3) {
                product.setDiscount(((product.getPricePerUnit().multiply(BigDecimal.valueOf(10))).divide(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(product.getQuantity())));
            } else {
                product.setDiscount(BigDecimal.ZERO);
            }
        });
        BigDecimal total = productList.stream()
                .map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())).subtract(product.getDiscount()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        return total;

    }

    public BigDecimal calculateTotalPriceTask3(ShoppingCart shoppingCart) {
        List<Product> productList = shoppingCart.getProducts();

        productList.forEach(product -> {
            if (getCategoryCountInList(productList,product.getCategory()) > 3) {
                product.setDiscount(((product.getPricePerUnit().multiply(BigDecimal.valueOf(10))).divide(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(product.getQuantity())));
            } else {
                product.setDiscount(BigDecimal.ZERO);
            }
        });
        BigDecimal total = productList.stream()
                .map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())).subtract(product.getDiscount()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        return total;

    }

    public BigDecimal calculateTotalPriceTask4(ShoppingCart shoppingCart) {
        Properties prop = new Properties();

        try {
            prop.load(ShoppingCartService.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(prop.getProperty("discount.percentage"));
         int discountPercentage = Integer.parseInt(prop.getProperty("discount.percentage"));

        List<Product> productList = shoppingCart.getProducts();

        productList.forEach(product -> {
            if (getCategoryCountInList(productList,product.getCategory()) > 3) {
                product.setDiscount(((product.getPricePerUnit().multiply(BigDecimal.valueOf(discountPercentage))).divide(BigDecimal.valueOf(100))).multiply(BigDecimal.valueOf(product.getQuantity())));
            } else {
                product.setDiscount(BigDecimal.ZERO);
            }
        });
        BigDecimal total = productList.stream()
                .map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())).subtract(product.getDiscount()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        return total;

    }
    public int getCategoryCountInList(List<Product> productList,String category){

        int categoryCount = productList.stream().filter(product -> product.getCategory().equals(category)).mapToInt(Product::getQuantity).sum();
        return categoryCount;

    }

}
