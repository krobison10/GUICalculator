package GUICalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public void actionPerformed(ActionEvent e) {
        getRd2().setSelected(false);
        if(CalculatorFunction.getMode().equals("Float"))
            CalculatorFunction.setMode("Integer");
    }
}
class btnFloatClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        getRd1().setSelected(false);
        if(CalculatorFunction.getMode().equals("Integer"))
            CalculatorFunction.setMode("Float");
    }
}
class btnCClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        //Do not want to wipe text unless it was entered by user
        if(!CalculatorFunction.isDisplayingResult() && !CalculatorFunction.isDisplayingUnaryResult())
            clearDisplayText();
    }
}
class btnACClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        clearPreviousNums();
        clearDisplayText();
        CalculatorFunction.reset();

    }
}
class btnSqrtClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!getDisplayText().equals(""))
            setDisplayText(String.valueOf(CalculatorFunction.unaryOperation
                    ("sqrt", getDisplayTextFloat())).replaceAll("\\.?0*$", ""));
    }
}
class btnxSquareClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!getDisplayText().equals(""))
            setDisplayText(String.valueOf(CalculatorFunction.unaryOperation
                    ("xsquare", getDisplayTextFloat())).replaceAll("\\.?0*$", ""));
    }
}
class btnRecipClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!getDisplayText().equals(""))
            if(CalculatorFunction.getMode().equals("Integer"))
            {
                CalculatorMain.showErrorMessage("Not available in integer mode");
            }
            else
            {
                setDisplayText(String.valueOf(CalculatorFunction.unaryOperation
                     ("reciprocal", getDisplayTextFloat()))
                        .replaceAll("\\.?0*$", ""));
            }
    }
}
class btnDivideClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorFunction.wipeEvent("/");
    }
}
class btnMinusClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorFunction.wipeEvent("-");
    }
}
class btnMultiplyClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {

        CalculatorFunction.wipeEvent("*");
    }
}
class btnAddClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorFunction.wipeEvent("+");
    }
}
class btnEqualsClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorFunction.wipeEvent("=");
    }
}
class btn1Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorMain.addToDisplay("1");
    }
}
class btn2Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorMain.addToDisplay("2");
    }
}
class btn3Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorMain.addToDisplay("3");
    }
}
class btn4Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorMain.addToDisplay("4");
    }
}
class btn5Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorMain.addToDisplay("5");
    }
}
class btn6Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorMain.addToDisplay("6");
    }
}
class btn7Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorMain.addToDisplay("7");
    }
}
class btn8Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorMain.addToDisplay("8");
    }
}
class btn9Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorMain.addToDisplay("9");
    }
}
class btn0Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorMain.addToDisplay("0");
    }
}
class btnDecimalClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        if(CalculatorFunction.getMode().equals("Integer"))
            showErrorMessage("Not available in integer mode");
        else
            CalculatorMain.addToDisplay(".");
    }
}
