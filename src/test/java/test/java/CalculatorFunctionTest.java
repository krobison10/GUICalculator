package test.java;

import GUICalculator.CalculatorFunction;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorFunctionTest
{
    @Test
    @DisplayName("Calculations: Add")
    public void testA()
    {
        assertEquals(10, CalculatorFunction.calculate(5, "+",5));
    }
    @Test
    @DisplayName("Calculations: Divide")
    public void testB()
    {
        assertNull(CalculatorFunction.calculate(1, "/", 0));
    }
}
