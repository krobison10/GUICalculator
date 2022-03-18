package GUICalculator;

import javax.swing.*;

import java.awt.*;

import static GUICalculator.CalculatorMain.*;

public class CalculatorFunction
{
    //Global calculation variables
    private static Integer firstNum = null;
    private static String lastOperation = null;
    private static Integer secondNum = null;
    private static Integer result = null;

    //Boolean variables representing that status of mutliple things
    private static boolean hasBothNums = false;
    private static boolean displayingResult = false;
    private static boolean newCalculation = true;


    /**
     * Method that processes the events that will wipe the calculator display. Handles
     * tasks like moving text from the main display to the label that displays the
     * previous number and operation
     * @param type is the operation type
     */
    public static void wipeEvent(String type)
    {
        //If no text in display do nothing
        if(getDisplayText().equals(""))
        {
            return;
        }
        //If on the second number, exit method when user attempts to add another operator
        if(firstNum != null && !type.equals("="))
        {
            return;
        }
        //Also exit method if user presses = while display is displaying a result
        if(type.equals("=") && displayingResult)
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
            firstNum = getDisplayTextInt();
        }
        //There's a first number, but not second, so populate second
        else if(secondNum == null)
        {
            secondNum = getDisplayTextInt();
            hasBothNums = true;
        }

        //Add the operator to the string
        addOperatorToDisplay(type);
        if(!type.equals("="))
            lastOperation = type;

        //Move the text up to the label and wipe the display
        addToPreviousNums(getDisplayText());
        clearDisplayText();

        //Calculate result if there are two numbers
        if(hasBothNums())
        {
            result = calculate(firstNum, lastOperation, secondNum);

            if (result == null) //Error Condition, reset calc
            {
                reset();
                clearPreviousNums();
                setDisplayText("Error");
                displayingResult = true;
                return;
            }
        }


        //If the operation is equals, reset calculator but display result
        if(type.equals("="))
        {
            if(hasBothNums())
                setDisplayText(String.valueOf(result));
            else
                setDisplayText(String.valueOf(firstNum));
            addToPreviousNums("=");
            reset();
            newCalculation = true;
            //Make sure displayingResult is true because the reset method sets it to false
            displayingResult = true;
        }
    }

    /**
     * Method that handles the calculations that don't wipe the calculator display
     * on execution. Calculations like exponents or square roots, better to be done in
     * place
     * @param type the type of operation
     */
    public static void nonWipeEvent(String type)
    {
        if(type.equals("xsquare"))
        {
            setDisplayText(String.valueOf((int)(Math.pow(getDisplayTextInt(), 2))));
        }
        if(type.equals("sqrt"))
        {
            setDisplayText(String.valueOf((int)(Math.sqrt(getDisplayTextInt()))));
        }
        if(type.equals("reciprocal"))
        {
            setDisplayText(String.valueOf(1 / getDisplayTextInt()));
        }
    }

    /**
     * Adds the operator character to the method. Takes the type of operation and
     * appends the proper character to the display or a unicode character if
     * necessary
     * @param type of operation
     */
    private static void addOperatorToDisplay(String type)
    {
        if(type.equals("+"))
        {
            addToDisplay("+");
        }
        if(type.equals("-"))
        {
            addToDisplay("-");
        }
        if(type.equals("*"))
        {
            addToDisplay("*");
        }
        if(type.equals("/"))
        {
            addToDisplay("\u00F7");
        }
    }

    /**
     * The main calculation method that uses the class variables to execute the calculation
     * and return the result to the wipeEvent method.
     * @return the calculation result
     */
    public static Integer calculate(int firstNum, String lastOperation, int secondNum) throws ArithmeticException
    {
        if(lastOperation.equals("/"))
        {
            try
            {
                return firstNum / secondNum;
            }
            catch (ArithmeticException e)
            {
                System.out.println(e);
                //Invoke later so everything isn't frozen until error pane is closed
                EventQueue.invokeLater( () -> JOptionPane.showMessageDialog(new JFrame(),
                        "Division by 0", "Error", JOptionPane.ERROR_MESSAGE));
                return null;

            }
        }
        if(lastOperation.equals("-"))
        {
            return firstNum - secondNum;
        }
        if(lastOperation.equals("*"))
        {
            return firstNum * secondNum;
        }
        if(lastOperation.equals("+"))
        {
            return firstNum + secondNum;
        }
        return 0;
    }

    /**
     * Method that resets the many variables that represent the calculator state, to be
     * called when the equals or all clear buttons are pressed
     */
    public static void reset()
    {
        firstNum = null;
        lastOperation = null;
        secondNum = null;
        result = null;

        displayingResult = false;
    }

    /**
     * @return true if both of the number variables are non-null
     */
    public static boolean hasBothNums()
    {
        return hasBothNums;
    }

    /**
     * @return true if the calculator is displaying a result from a calculation
     * rather than user-entered text
     */
    public static boolean isDisplayingResult()
    {
        return displayingResult;
    }

    /**
     * Sets the variable that represents whether the calculator is displaying a
     * result or not
     * @param in the value to be applied
     */
    public static void setDisplayingResult(boolean in)
    {
        displayingResult = in;
    }
}
