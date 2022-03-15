package GUICalculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUICalculator.CalculatorMain.*;

class btnRdOnClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        rd2.setSelected(false);
    }
}
class btnRdOffClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        rd1.setSelected(false);
    }
}
class btnCClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
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
        addToDisplay("sqrt");
    }
}
class btnxSquareClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        addToDisplay("^2");
    }
}
class btnRecipClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        setDisplayText("1/(" + getDisplayText() + " )");
    }
}
class btnDivideClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorFunction.wipeEvent("divide");
    }
}
class btnMinusClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorFunction.wipeEvent("minus");
    }
}
class btnMultiplyClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorFunction.wipeEvent("multiply");
    }
}
class btnAddClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorFunction.wipeEvent("add");
    }
}
class btnEqualsClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorFunction.wipeEvent("equals");
    }
}
class btn1Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        addToDisplay("1");
    }
}
class btn2Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        addToDisplay("2");
    }
}
class btn3Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        addToDisplay("3");
    }
}
class btn4Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        addToDisplay("4");
    }
}
class btn5Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        addToDisplay("5");
    }
}
class btn6Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        addToDisplay("6");
    }
}
class btn7Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        addToDisplay("7");
    }
}
class btn8Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        addToDisplay("8");
    }
}
class btn9Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        addToDisplay("9");
    }
}
class btn0Click implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        addToDisplay("0");
    }
}
class btnDecimalClick implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(new JFrame(), "Not implemented yet", "Error",
        JOptionPane.ERROR_MESSAGE);
    }
}
