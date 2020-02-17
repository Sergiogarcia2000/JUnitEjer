package com.gmq.entornos.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    PriceCalculator priceCalculatorTest = new PriceCalculator();

    @BeforeEach
    void setUp() {
    }

    private static Stream<Arguments> sumArgs(){
        return Stream.of(
                Arguments.of(new Integer[]{3,4,1,4}, 12),
                Arguments.of(new Integer[]{-4,4,1,4}, 5)
        );
    }

    private static Stream<Arguments> priceArgs(){
        return Stream.of(
                Arguments.of("CAR", 3, 15300),
                Arguments.of("HOUSE", 1, 56000),
                Arguments.of("APPARTMENT", 3, 96000),
                Arguments.of("TRUCK", 2, 40800),
                Arguments.of("FOOD", 1, 3.5),
                Arguments.of("PLANE", 2, 3080000),
                Arguments.of("HELICOPTER", 1, 616000),
                Arguments.of("BIKE", 3, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("sumArgs")
    void sumProductsPrice(Integer[] nums, int result) {
        assertEquals(result, priceCalculatorTest.sumProductsPrice(nums));
    }

    @ParameterizedTest
    @MethodSource("priceArgs")
    void calculatePrice(String name, Integer num, double solution) {
        double result = priceCalculatorTest.calculatePrice(name, num);
        assertEquals(solution, result);
    }
}