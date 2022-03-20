package GUICalculator;

import java.io.IOException;

import static GUICalculator.CalculatorMain.*;

public class CalculatorFunction
{
    //Global calculation variables
    private static Float firstNum = null;
    private static String lastOperation = null;
    private static Float secondNum = null;
    private static Float result = null;

    private static String mode = "Integer";

    //Boolean variables representing that status of multiple things
    private static boolean hasBothNums = false;
    private static boolean displayingResult = false;
    private static boolean newCalculation = true;
    private static boolean displayingUnaryResult = false;


    /**
     * Method that processes the operations that will wipe the calculator display. Handles
     * tasks like moving text from the main display to the label that displays the
     * previous number and operation
     * @param type is the operation type
     */
    static void operation(String type) throws IOException
    {
        //All the break cases, any and all states that prevent proper function
        if( getDisplayText().equals("") /*Display is empty*/
        || (firstNum != null && !type.equals("=")) /*User tried to begin a chain operation*/
        || (type.equals("=") && displayingResult) /*user tried to press = when calc was displaying result*/
        || getDisplayTextFloat() == null) /*there was an integer parse error for the input*/
        {
            return;
        }
        //If a new calculation is initiated, reset and wipe the previous calculations
        if(newCalculation)
        {
            reset();
            clearPreviousNums();
            newCalculation = false;
        }
        //There is text, so figure out if it will be first, second
        hasBothNums = false;

        if(firstNum == null)
        {
            firstNum = getDisplayTextFloat();
            InputLogger.addFirstNum(String.valueOf(firstNum).replaceAll("\\.?0*$", ""));
        }
        //There's a first number, but not second, so populate second
        else if(secondNum == null)
        {
            secondNum = getDisplayTextFloat();
            InputLogger.addSecondNum(String.valueOf(secondNum).replaceAll("\\.?0*$", ""));
            hasBothNums = true;
        }
        //Add the operator to the string
        addToDisplay(getUnicodeOperator(type), true);
        if(!type.equals("="))
            lastOperation = type;

        //Move the text up to the label and wipe the display
        addToPreviousNums(getDisplayText());
        clearDisplayText();

        //Calculate result if there are two numbers
        if(hasBothNums())
        {
            result = calculate(firstNum, lastOperation, secondNum);
            InputLogger.addOperationAndResult(lastOperation,
                    String.valueOf(result).replaceAll("\\.?0*$", ""));
            if (result == null) //Error Condition, reset calc
            {
                error("");
                return;
            }
        }

        //If the operation is equals, reset calculator but display result
        if(type.equals("="))
        {
            if(hasBothNums())
                setDisplayText(String.valueOf(result).replaceAll("\\.?0*$", ""));
            else
                setDisplayText(String.valueOf(firstNum).replaceAll("\\.?0*$", ""));
            addToPreviousNums("=");
            reset();
            newCalculation = true;
            //Make sure displayingResult is true because the reset method sets it to false
            displayingResult = true;
        }
    }

    /**
     * Method that sets the calculator into an error state, used in very few
     * circumstances but an important failsafe nonetheless
     */
    static void error(String message) throws IOException
    {
        InputLogger.addErrorMessage(message);
        reset();
        clearPreviousNums();
        setDisplayText("Error");
        displayingResult = true;
    }

    /**
     * Method that resets the many variables that represent the calculator state, to be
     * called when the equals or all clear buttons are pressed
     */
    static void reset() throws IOException
    {
        firstNum = null;
        lastOperation = null;
        secondNum = null;
        result = null;

        InputLogger.closeLine();
        InputLogger.newLog();

        displayingResult = false;
    }
    /**
     * Changes the operation mode of the calculator
     */
    static void changeMode(String newMode) throws IOException
    {
        mode = newMode;
        clearPreviousNums();
        clearDisplayText();
        reset();
    }

    /**
     * Method that handles the unary operations
     * @param type the type of operation
     */
    public static Float unaryOperation(String type, Float number) throws ArithmeticException
    {
        //Do nothing if there is nothing to operate on
        if(number == null)
        {
            return null;
        }
        try
        {
            if(type.equals("xsquare"))
            {
                if(mode.equals("Integer"))
                {
                    //Cast to int first to make sure it is a valid int
                    displayingUnaryResult = true;
                    return (float) ((int) (Math.pow(number, 2)));
                }
                displayingUnaryResult = true;
                return (float)(Math.pow(number, 2));
            }

            if(type.equals("sqrt"))
            {
                float result = (float) Math.sqrt(number);
                if(mode.equals("Integer"))
                {
                    if(result != Math.round(result))
                    {
                        showErrorMessage("Answer is not a whole number");
                        return number;
                    }
                    else
                    {
                        displayingUnaryResult = true;
                        return result;
                    }
                }
                displayingUnaryResult = true;
                return result;
            }

            //For the integer calculator, I have this function disabled, because the only
            //operand that yields an integer is 1
            if(type.equals("reciprocal"))
            {
                displayingUnaryResult = true;
                return ((1 / number));
            }
        }
        catch(Exception e)
        {
            showErrorMessage(e.toString());
            displayingUnaryResult = false;
            return null;
        }
        return null;
    }

    /**
     * Returns the unicode version of the operator if required.
     * @param type of operation
     */
    private static String getUnicodeOperator(String type)
    {
        if(type.equals("+"))
        {
            return "+";
        }
        if(type.equals("-"))
        {
            return "-";
        }
        if(type.equals("*"))
        {
            return "*";
        }
        if(type.equals("/"))
        {
            //Unicode division character
            return "\u00F7";
        }
        return "";
    }
    /**
     * The main calculation method that uses the class variables to execute the calculation
     * and return the result to the wipeEvent method. Uses special Math class methods to
     * simplify the process of throwing an exception for an integer overflow exception.
     * Java gave us these amazing libraries for a reason!
     * @return the calculation result
     */
    public static Float calculate(float firstNum, String lastOperation, float secondNum) throws ArithmeticException
    {
        try
        {
            int firstInt = 0;
            int secondInt = 0;
            if(mode.equals("Integer"))
            {
                firstInt = (int) firstNum;
                secondInt = (int) secondNum;
            }
            if(lastOperation.equals("/") && !mode.equals("Float"))
            {
                if(secondNum == 0)
                    return null;
                return (float) firstInt / secondInt;
            }
            else if(lastOperation.equals("/"))
            {
                if(secondNum == 0)
                    return null;
                return firstNum / secondNum;
            }
            if(lastOperation.equals("-") && !mode.equals("Float"))
            {
                return (float) Math.subtractExact(firstInt, secondInt);
            }
            else if(lastOperation.equals("-"))
            {
                return firstNum - secondNum;
            }
            if(lastOperation.equals("*") && !mode.equals("Float"))
            {
                return (float) Math.multiplyExact(firstInt, secondInt);
            }
            else if(lastOperation.equals("*"))
            {
                return firstNum * secondNum;
            }
            if(lastOperation.equals("+") && !mode.equals("Float"))
            {
                return (float) Math.addExact(firstInt, secondInt);
            }
            else if(lastOperation.equals("+"))
            {
                return firstNum + secondNum;
            }
            return null;
        }
        catch(Exception e)
        {

            showErrorMessage(e.toString());
            return null;
        }
    }

    //region Getters/Setters
    /**
     * @return true if the display is displaying the result of a unary calculation
     */
    static boolean isDisplayingUnaryResult()
    {
        return displayingUnaryResult;
    }
    /**
     * Sets the variable that represents whether the display is showing the result of a unary calculation
     */
    static void setDisplayingUnaryResultFalse()
    {
        displayingUnaryResult = false;
    }
    /**
     * @param newMode the new calculator mode
     */
    public static void setMode(String newMode) throws IOException
    {
        mode = newMode;
        changeMode(newMode);
    }
    /**
     * @return true if both of the number variables are non-null
     */
    static boolean hasBothNums()
    {
        return hasBothNums;
    }
    /**
     * @return true if the calculator is displaying a result from a calculation
     * rather than user-entered text
     */
    static boolean isDisplayingResult()
    {
        return displayingResult;
    }
    /**
     * Sets the variable that represents whether the calculator is displaying a
     * result or not
     */
    static void setDisplayingResult()
    {
        displayingResult = false;
    }
    /**
     * @return the current calculator mode
     */
    static String getMode()
    {
        return mode;
    }
    //endregion
}
