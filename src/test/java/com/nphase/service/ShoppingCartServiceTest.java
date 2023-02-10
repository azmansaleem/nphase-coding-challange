package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.naming.OperationNotSupportedException;
import java.math.BigDecimal;
import java.util.Arrays;

public class ShoppingCartServiceTest {
    private final ShoppingCartService service = new ShoppingCartService();

    @Test
    public void calculatesPrice()  {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 2),
                new Product("Coffee", BigDecimal.valueOf(6.5), 1)
        ));

        BigDecimal result = service.calculateTotalPriceTask1(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(16.5));

    }

    @Test
    public void calculatesPriceTask2Test1()  {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 5),
                new Product("Coffee", BigDecimal.valueOf(3.5), 3)
        ));

        BigDecimal result = service.calculateTotalPriceTask2(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(33.0));

    }

    @Test
    public void calculatesPriceTask2Test2()  {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 5),
                new Product("Coffee", BigDecimal.valueOf(5.0), 5)
        ));

        BigDecimal result = service.calculateTotalPriceTask2(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(45.0));

    }

    @Test
    public void calculatesPriceTask3Test1()  {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.3), 2,"drinks"),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2,"drinks"),
                new Product("cheese", BigDecimal.valueOf(8), 2,"food")
        ));
//        Expected total is: 9.54 (for tea) + 6.30 (for coffee) + 16 (for cheese) = 31.84
        BigDecimal result = service.calculateTotalPriceTask3(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(31.84));

    }

    @Test
    public void calculatesPriceTask3Test2()  {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(10.3), 2,"drinks"),
                new Product("Coffee", BigDecimal.valueOf(5.4), 2,"drinks"),
                new Product("BlackCoffee", BigDecimal.valueOf(3.5), 2,"drinks"),
                new Product("cheese", BigDecimal.valueOf(15), 2,"food"),
                new Product("bread", BigDecimal.valueOf(11.32), 2,"food"),
                new Product("tShirt", BigDecimal.valueOf(8), 2,"dress")
        ));
        BigDecimal result = service.calculateTotalPriceTask3(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(97.936));

    }

    //with configurable percentage
    @Test
    public void calculatesPriceTask4Test()  {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(10.3), 2,"drinks"),
                new Product("Coffee", BigDecimal.valueOf(5.4), 2,"drinks"),
                new Product("BlackCoffee", BigDecimal.valueOf(3.5), 2,"drinks"),
                new Product("cheese", BigDecimal.valueOf(15), 2,"food"),
                new Product("bread", BigDecimal.valueOf(11.32), 2,"food"),
                new Product("tShirt", BigDecimal.valueOf(8), 2,"dress")
        ));
        BigDecimal result = service.calculateTotalPriceTask4(cart);
        Assertions.assertEquals(result, BigDecimal.valueOf(97.936));

    }

}