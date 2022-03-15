package GUICalculator;

import static GUICalculator.CalculatorMain.*;

public class CalculatorFunction
{
    private static Integer lastNum = null;
    private static Integer thisNum = null;
    public static  boolean displayingResult = false;
    public static void wipeEvent(String type)
    {
        if(lastNum == null)
        {
            lastNum = getTextInt();
        }
        if(type.equals("divide"))
        {
            addToDisplay("\u00F7");
        }
        if(type.equals("minus"))
        {
            addToDisplay("-");
        }
        if(type.equals("multiply"))
        {
            addToDisplay("x");
        }
        if(type.equals("add"))
        {
            addToDisplay("+");
        }
        if(type.equals("equals"))
        {
            addToDisplay("=");
        }
        addToPreviousNums(getDisplayText());
        clearDisplayText();
    }
    public static void nonWipeCalculation(String type)
    {

    }
    public static int calculate(int num1, int num2, String operation)
    {
        if(operation.equals("add"))
        {
            return num1 + num2;
        }
        if(operation.equals("subtract"))
        {
            return num1 - num2;
        }
        return 1;
    }
    public static void reset()
    {
        lastNum = null;
        thisNum = null;
        displayingResult = false;
    }
}
