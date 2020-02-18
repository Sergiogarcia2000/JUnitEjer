package com.gmq.entornos.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    private PriceCalculator priceCalculatorTest = new PriceCalculator();

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
                Arguments.of("CAR", 1, 19650),
                Arguments.of("HOUSE", 1, 395499.99999999994),
                Arguments.of("APPARTMENT", 3, 677999.9999999999),
                Arguments.of("TRUCK", 2, 157200.0),
                Arguments.of("FOOD", 1, 52),
                Arguments.of("PLANE", 2, 9870000.0),
                Arguments.of("HELICOPTER", 1, 1974000.0),
                Arguments.of("noexisto", 1, 0),
                Arguments.of("BIKE", 3, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("sumArgs")
    void Should_Sum_Products_Price(Integer[] nums, int result) {
        assertEquals(result, priceCalculatorTest.sumProductsPrice(nums));
    }

    @ParameterizedTest
    @MethodSource("priceArgs")
    void Should_Calculate_Price(String name, Integer num, double solution) {
        double result = priceCalculatorTest.calculatePrice(name, num);
        assertEquals(solution, result);
    }
}