package test.java;

import GUICalculator.CalculatorFunction;
import GUICalculator.CalculatorMain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Note about this class, due to the structure of my mode switching system,
 * the tests must be all run individually, but they all pass in that scenario
 */
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
    @DisplayName("Calculations: Basic Decimals")
    void test11()
    {
        CalculatorFunction.changeMode("Float");
        assertEquals(3.2, CalculatorFunction.calculate(1.6, "+" ,1.6));
        assertEquals(0.8, CalculatorFunction.calculate(1.6, "-" ,0.8));
        assertEquals(5, CalculatorFunction.calculate(10, "*" ,0.5));
        assertEquals(5, CalculatorFunction.calculate(2.5, "/" ,0.5));
    }
    @Test
    @DisplayName("Calculations: Integer Overflows")
    void test5()
    {
        assertNull(CalculatorFunction.calculate(2147483647, "*", 2147483647));
        assertNull(CalculatorFunction.calculate(2147483647, "+", 2147483647));
        assertNull(CalculatorFunction.calculate(-2147483647, "-", 2147483647));
    }
    @Test
    @DisplayName("Inputs integer too large")
    void test6()
    {
        CalculatorMain.setDisplayText("87544375478747594837");
        assertNull(CalculatorMain.getDisplayTextDouble());
    }
    @Test
    @DisplayName("Unary: x^2")
    void test7()
    {
        assertEquals(16d, CalculatorFunction.unaryOperation("xsquare", 4d) );
    }
    @Test
    @DisplayName("Unary: sqrt(x)")
    void test8()
    {
        assertEquals(5d, CalculatorFunction.unaryOperation("sqrt", 25d) );
    }
    @Test
    @DisplayName("Unary: sqrt with non-integer result")
    void test9()
    {
        /*The error handling in this method works by not doing the operation if there's an error
        so the result should equal the input in the error case*/
        assertEquals(5d, CalculatorFunction.unaryOperation("sqrt", 5d) );
        assertEquals(10d, CalculatorFunction.unaryOperation("sqrt", 10d) );
    }
    @Test
    @DisplayName("Unary: Reciprocal")
    void test12()
    {
        CalculatorFunction.changeMode("float");
        assertEquals(0.25, CalculatorFunction.unaryOperation("reciprocal",4d));
        assertEquals(0.5, CalculatorFunction.unaryOperation("reciprocal",2d));
        assertNull(CalculatorFunction.unaryOperation("reciprocal", 0d));
    }
}
