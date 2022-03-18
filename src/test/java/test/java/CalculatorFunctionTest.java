package test.java;

import GUICalculator.CalculatorFunction;
import GUICalculator.CalculatorMain;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorFunctionTest
{
    @Test
    @DisplayName("Calculations: Add")
    void test1()
    {
        //Basic Operation
        assertEquals(10, CalculatorFunction.calculate(5, "+",5));
        //Big Number
        assertEquals(211547914, CalculatorFunction.calculate(5647, "*", 37462));
    }
    @Test
    @DisplayName("Calculations: Subtract")
    void test2()
    {
        //Basic
        assertEquals(5, CalculatorFunction.calculate(10,"-", 5));
        //Big Number
        assertEquals(31815, CalculatorFunction.calculate(37462, "-", 5647));
    }
    @Test
    @DisplayName("Calculations: Multiply")
    void test3()
    {
        //Basic
        assertEquals(25, CalculatorFunction.calculate(5, "*", 5));
        //Big Number
        assertEquals(211547914, CalculatorFunction.calculate(5647, "*", 37462));
    }
    @Test
    @DisplayName("Calculations: Divide")
    void test4()
    {
        //Basic
        assertEquals(5, CalculatorFunction.calculate(25, "/", 5));
        //Big Number
        assertEquals(10, CalculatorFunction.calculate(10000000, "/", 1000000));
        //Division by 0
        assertNull(CalculatorFunction.calculate(1, "/", 0));
    }
    @Test
    @DisplayName("Calculations: Integer Overflows")
    void test5()
    {
        //Method will return null in error state so asserting null is more concise for this test
        CalculatorFunction.setMode("Integer");
        assertNull(CalculatorFunction.calculate(2147483647, "*", 2147483647));
        assertNull(CalculatorFunction.calculate(2147483647, "+", 2147483647));
        assertNull(CalculatorFunction.calculate(-2147483647, "-", 2147483647));
    }
    @Test
    @DisplayName("User inputs integer too large")
    void test6()
    {
        CalculatorFunction.setMode("Integer");
        CalculatorMain.setDisplayText("87544375478747594837");
        assertNull(CalculatorMain.getDisplayTextFloat());
    }
    @Test
    @DisplayName("Unary: x^2")
    void test7()
    {
        //4^2
        assertEquals(16f, CalculatorFunction.unaryOperation("xsquare", 4f) );
    }
    @Test
    @DisplayName("Unary: sqrt(x)")
    void test8()
    {
        //4^2
        assertEquals(5f, CalculatorFunction.unaryOperation("sqrt", 25f) );
    }
    @Test
    @DisplayName("Unary: sqrt with non-integer result")
    void test9()
    {
        /*The error handling in this method works by not doing the operation if there's an error
        so the result should equal the input in the error case*/
        CalculatorFunction.setMode("Integer");
        assertEquals(5, CalculatorFunction.unaryOperation("sqrt", 5f) );
    }
}
