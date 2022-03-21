package GUICalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUICalculator.CalculatorFunction.df;
import static GUICalculator.CalculatorMain.*;

/**
 * Every single class in this file contains one single event handler method,
 * all they do is call some method from sort of method and tell that method
 * what kind of button it is. All the methods that these event handlers
 * call need a type variable, which specifies the value of the button.
 * Some methods do basic input checking
 */
class btnIntClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        getRadioFloat().setSelected(false);
        if(CalculatorFunction.getMode().equals("Float"))
        {
            CalculatorFunction.changeMode("Integer");
        }
    }
}
class btnFloatClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        getRadioInt().setSelected(false);
        if(CalculatorFunction.getMode().equals("Integer"))
        {
            CalculatorFunction.changeMode("Float");
        }
    }
}
class btnCClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Do not want to wipe text unless it was entered by user
        if(!CalculatorFunction.isDisplayingResult())
        {
            if (CalculatorFunction.isDisplayingUnaryResult())
            {
                CalculatorFunction.setDisplayingUnaryResultFalse();//Possible trouble point
            }
            clearDisplayText();
        }
    }
}
class btnACClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        clearPreviousNums();
        clearDisplayText();
        CalculatorFunction.reset();

    }
}
class btnSqrtClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equals("") && !getDisplayText().equalsIgnoreCase("error"))
        {
            setDisplayText(df.format(CalculatorFunction.unaryOperation
                ("sqrt", getDisplayTextDouble())));
        }
    }
}
class btnxSquareClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equals("") && !getDisplayText().equalsIgnoreCase("error"))
        {
            setDisplayText(df.format(CalculatorFunction.unaryOperation
                    ("xsquare", getDisplayTextDouble())));
        }
    }
}
class btnRecipClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equals("") && !getDisplayText().equalsIgnoreCase("error"))
        {
            setDisplayText(df.format(CalculatorFunction.unaryOperation
                    ("reciprocal", getDisplayTextDouble())));
        }
    }
}
class btnDivideClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equals("") && !getDisplayText().equalsIgnoreCase("error"))
        {
            CalculatorFunction.operation("/");
        }
    }
}
class btnMinusClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equals("") && !getDisplayText().equalsIgnoreCase("error"))
        {
            CalculatorFunction.operation("-");
        }
    }
}
class btnMultiplyClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {

        if(!getDisplayText().equals("") && !getDisplayText().equalsIgnoreCase("error"))
        {
            CalculatorFunction.operation("*");
        }
    }
}
class btnAddClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equals("") && !getDisplayText().equalsIgnoreCase("error"))
        {
            CalculatorFunction.operation("+");
        }
    }
}
class btnEqualsClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equals("") && !getDisplayText().equalsIgnoreCase("error"))
        {
            CalculatorFunction.operation("=");
        }
    }
}
class btn1Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CalculatorMain.addToDisplay("1", false);
    }
}
class btn2Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CalculatorMain.addToDisplay("2", false);
    }
}
class btn3Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CalculatorMain.addToDisplay("3", false);
    }
}
class btn4Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CalculatorMain.addToDisplay("4", false);
    }
}
class btn5Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CalculatorMain.addToDisplay("5", false);
    }
}
class btn6Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CalculatorMain.addToDisplay("6", false);
    }
}
class btn7Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CalculatorMain.addToDisplay("7", false);
    }
}
class btn8Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CalculatorMain.addToDisplay("8", false);
    }
}
class btn9Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CalculatorMain.addToDisplay("9", false);
    }
}
class btn0Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        CalculatorMain.addToDisplay("0", false);
    }
}
class btnDecimalClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().contains("."))
        {
            CalculatorMain.addToDisplay(".", false);
        }
    }
}
