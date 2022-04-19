package GUICalculator;

import java.text.DecimalFormat;

import static GUICalculator.CalculatorMain.*;

public class CalculatorFunction
{
    //region Static Fields
    private static Double firstNum = null, secondNum = null, result = null;
    private static String lastOperation = null, mode = "Integer";

    public static final DecimalFormat df = new DecimalFormat("0");

    private static boolean hasBothNums = false, displayingResult = false,
            newCalculation = true, displayingUnaryResult = false;

    //endregion


    /**
     * Method that processes the operations that will wipe the calculator display. Handle
     * tasks like moving text from the main display to the label that displays the
     * previous number and operation
     * @param type is the operation type
     */
    static void operation(String type) {
        //All the break cases, any and all states that prevent proper function
        if( getDisplayText().equals("") /*Display is empty*/
        || (firstNum != null && !type.equals("=")) /*User tried to begin a chain operation*/
        || (type.equals("=") && displayingResult) /*user tried to press = when calc was displaying result*/
        || getDisplayTextDouble() == null) /*there was an integer parse error for the input*/
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
            firstNum = getDisplayTextDouble();
            InputLogger.addFirstNum(df.format(firstNum));
        }
        //There's a first number, but not second, so populate second
        else if(secondNum == null)
        {
            secondNum = getDisplayTextDouble();
            InputLogger.addSecondNum(df.format(secondNum));
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
                    df.format(result));
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
                setDisplayText(df.format(result));
            else
                setDisplayText(df.format(firstNum));
            addToPreviousNums("=");
            reset();
            newCalculation = true;
            //Make sure displayingResult is true because the reset method sets it to false
            displayingResult = true;
        }
    }

    /**
     * The main calculation method that uses the class variables to execute the calculation
     * and return the result to the wipeEvent method. Uses special Math class methods to
     * simplify the process of throwing an exception for an integer overflow exception.
     * Java gave us these amazing libraries for a reason!
     * @return the calculation result
     */
    public static Double calculate(double firstNum, String lastOperation, double secondNum) throws ArithmeticException
    {
        try
        {
            Double result = null;
            int firstInt = 0;
            int secondInt = 0;
            if(mode.equals("Integer"))
            {
                firstInt = (int) firstNum;
                secondInt = (int) secondNum;
                if(lastOperation.equals("/"))
                {
                    if(secondNum == 0)
                        throw new ArithmeticException("Division by 0");
                    result = (double) Math.round(firstNum / secondNum);
                }
                if(lastOperation.equals("-"))
                {
                    result = (double) Math.subtractExact(firstInt, secondInt);
                }
                if(lastOperation.equals("*"))
                {
                    result = (double) Math.multiplyExact(firstInt, secondInt);
                }
                if(lastOperation.equals("+"))
                {
                    result = (double) Math.addExact(firstInt, secondInt);
                }
            }
            if(lastOperation.equals("/"))
            {
                if(secondNum == 0)
                    throw new ArithmeticException("Division by 0");
                result = firstNum / secondNum;
            }
            if(lastOperation.equals("-"))
            {
                result = firstNum - secondNum;
            }
            if(lastOperation.equals("*"))
            {
                result = firstNum * secondNum;
            }
            if(lastOperation.equals("+"))
            {
                result = firstNum + secondNum;
            }
            return result;
        }
        catch(Exception e)
        {
            showErrorMessage(e.toString());
            error(e.toString());
            return null;
        }
    }

    /**
     * Method that handles the unary operations
     * @param type the type of operation
     */
    public static Double unaryOperation(String type, Double number) throws ArithmeticException
    {
        //Do nothing if there is nothing to operate on
        if(number != null)
        {
            try
            {
                if(type.equals("xsquare"))
                {
                    if(mode.equals("Integer"))
                    {
                        //Cast to int first to make sure it is a valid int
                        displayingUnaryResult = true;
                        return (double) ((int) (Math.pow(number, 2)));
                    }
                    displayingUnaryResult = true;
                    return (Math.pow(number, 2));
                }

                if(type.equals("sqrt"))
                {
                    double result = Math.sqrt(number);
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

            /* For the integer calculator, I have this function disabled, because the only
            operand that yields an integer is 1 */
                if(type.equals("reciprocal"))
                {
                    if(number == 0)
                        throw new ArithmeticException("Division by 0");

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
        }
        return null;
    }

    /**
     * Method that sets the calculator into an error state, used in very few
     * circumstances but an important failsafe nonetheless
     */
    static void error(String message)
    {
        InputLogger.addOperationAndResult(lastOperation, "");
        reset();
        clearPreviousNums();
        setDisplayText("Error");
        displayingResult = true;
    }

    /**
     * Method that resets the many variables that represent the calculator state, to be
     * called when the equals or all clear buttons are pressed
     */
    static void reset()
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
    public static void changeMode(String newMode)
    {
        if(newMode.equalsIgnoreCase("Integer"))
        {
            getButton(".").setEnabled(false);
            getButton("1/x").setEnabled(false);
        }
        else
        {
            getButton(".").setEnabled(true);
            getButton("1/x").setEnabled(true);
        }
        mode = newMode;
        clearPreviousNums();
        clearDisplayText();
        reset();
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
