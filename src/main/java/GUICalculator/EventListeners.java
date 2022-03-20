package GUICalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static GUICalculator.CalculatorFunction.df;
import static GUICalculator.CalculatorMain.*;

/**
 * Every single class in this file contains one single event handler method,
 * all they do is call some method from sort of method and tell that method
 * what kind of button it is. All the methods that these event handlers
 * call need a type variable, which specifies the value of the button.
 */
class btnIntClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        getRd2().setSelected(false);
        if(CalculatorFunction.getMode().equals("Float"))
        {
            try { CalculatorFunction.setMode("Integer"); }
            catch (IOException ex) { ex.printStackTrace(); }
        }
    }
}
class btnFloatClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        getRd1().setSelected(false);
        if(CalculatorFunction.getMode().equals("Integer"))
        {
            try { CalculatorFunction.setMode("Float"); }
            catch (IOException ex) { ex.printStackTrace(); }
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
        try { CalculatorFunction.reset(); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
class btnSqrtClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equals("")  && !getDisplayText().equalsIgnoreCase("null")
                && !getDisplayText().equalsIgnoreCase("error")
                && !getDisplayText().equalsIgnoreCase("infinity"))
        {
            try {
                setDisplayText(df.format(CalculatorFunction.unaryOperation
                    ("sqrt", getDisplayTextFloat())));
            }
            catch (IOException ex) { ex.printStackTrace(); }
        }
    }
}
class btnxSquareClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equals("") && !getDisplayText().equalsIgnoreCase("null")
                && !getDisplayText().equalsIgnoreCase("error")
                && !getDisplayText().equalsIgnoreCase("infinity"))
        {
            try
            {
                setDisplayText(df.format(CalculatorFunction.unaryOperation
                        ("xsquare", getDisplayTextFloat())));
            }
            catch (IOException ex) { ex.printStackTrace(); }
        }
    }
}
class btnRecipClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equals("") && !getDisplayText().equalsIgnoreCase("null")
                && !getDisplayText().equalsIgnoreCase("error")
                && !getDisplayText().equalsIgnoreCase("infinity"))
        {
            if (CalculatorFunction.getMode().equals("Integer"))
            {
                CalculatorMain.showErrorMessage("Not available in integer mode");
            }
            else
            {
                try
                {
                    setDisplayText(df.format(CalculatorFunction.unaryOperation
                                    ("reciprocal", getDisplayTextFloat())));
                }
                catch (IOException ex) { ex.printStackTrace(); }
            }
        }
    }
}
class btnDivideClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equalsIgnoreCase("null")
                && !getDisplayText().equalsIgnoreCase("error")
                && !getDisplayText().equalsIgnoreCase("infinity"))
        {
            try
            {
                CalculatorFunction.operation("/");
            }
            catch (IOException ex) { ex.printStackTrace(); }
        }
    }
}
class btnMinusClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equalsIgnoreCase("null")
                && !getDisplayText().equalsIgnoreCase("error")
                && !getDisplayText().equalsIgnoreCase("infinity"))
        {
            try { CalculatorFunction.operation("-"); }
            catch (IOException ex) { ex.printStackTrace(); }
        }
    }
}
class btnMultiplyClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {

        if(!getDisplayText().equalsIgnoreCase("null")
                && !getDisplayText().equalsIgnoreCase("error")
                && !getDisplayText().equalsIgnoreCase("infinity"))
        {
            try { CalculatorFunction.operation("*"); }
            catch (IOException ex) { ex.printStackTrace(); }
        }
    }
}
class btnAddClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equalsIgnoreCase("null")
                && !getDisplayText().equalsIgnoreCase("error")
                && !getDisplayText().equalsIgnoreCase("infinity"))
        {
            try { CalculatorFunction.operation("+"); }
            catch (IOException ex) { ex.printStackTrace(); }
        }
    }
}
class btnEqualsClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().equalsIgnoreCase("null")
                || !getDisplayText().equalsIgnoreCase("error")
                && !getDisplayText().equalsIgnoreCase("infinity"))
        {
            try { CalculatorFunction.operation("="); }
            catch (IOException ex) { ex.printStackTrace(); }
        }
    }
}
class btn1Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try { CalculatorMain.addToDisplay("1", false); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
class btn2Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try { CalculatorMain.addToDisplay("2", false); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
class btn3Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try { CalculatorMain.addToDisplay("3", false); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
class btn4Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try { CalculatorMain.addToDisplay("4", false); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
class btn5Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try { CalculatorMain.addToDisplay("5", false); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
class btn6Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try { CalculatorMain.addToDisplay("6", false); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
class btn7Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try { CalculatorMain.addToDisplay("7", false); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
class btn8Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try { CalculatorMain.addToDisplay("8", false); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
class btn9Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try { CalculatorMain.addToDisplay("9", false); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
class btn0Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        try { CalculatorMain.addToDisplay("0", false); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
class btnDecimalClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!getDisplayText().contains("."))
        {
            try { CalculatorMain.addToDisplay(".", false); }
            catch (IOException ex) { ex.printStackTrace(); }
        }
    }
}
