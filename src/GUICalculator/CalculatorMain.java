package GUICalculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;

/**
 * @version 1.2 - still does nothing but looks pretty
 * @author Kyler Robison
 */

public class CalculatorMain
{
    //declare some components
    private static final JFrame calcWindow = new JFrame();
    private static final JTextField calcDisplay = new JTextField();
    private static final JLabel prevNums = new JLabel("");
    private static final JRadioButton rd1 = new JRadioButton();
    private static final JRadioButton rd2 = new JRadioButton();

    //Create set containing the buttons
    private static final Map<String, JButton> buttons = initializeButtons("0", "1", "2", "3", "4",
    "5", "6", "7", "8", "9", "AC", "CE", "\u00F7", "-", "x", "+", "=", ".", "\u221A", ("x"+"\u00B2"), "1/x");

    public static void main(String[] args)
    {
        EventQueue.invokeLater(CalculatorMain::initializeWindow);
    }

    //region CalcLabel methods

    /**
     * Adds text to the calculator display, if it is displaying a result when user
     * attempts to enter a number, it moves the result up to the previous
     * calculation label and begins accepting new numbers
     * @param text is the text to be appended to the label
     */
    public static void addToDisplay(String text)
    {
        if(CalculatorFunction.isDisplayingResult())//if the label is displaying a result
        {
            addToPreviousNums(calcDisplay.getText());//move result up to label bar
            clearDisplayText();//Clear the display
            CalculatorFunction.setDisplayingResult(false);
        }
        calcDisplay.setText(calcDisplay.getText() + text);
    }

    /**
     * Overwrites the value of the display
     * @param text is the text to be entered
     */
    public static void setDisplayText(String text)
    {
        calcDisplay.setText(text);
    }

    /**
     * Clears the text in the display
     */
    public static void clearDisplayText()
    {
        calcDisplay.setText("");
    }
    /**
     * @return the text in the calculator display
     */
    public static String getDisplayText()
    {
        return calcDisplay.getText();
    }
    /**
     * @return an integer format of the text in the calculator display
     */
    public static int getDisplayTextInt()
    {
        try
        {
            return Integer.parseInt(calcDisplay.getText());
        }
        catch (Exception e)
        {
            System.out.println("Parse error" + e);
            return 1;
        }
    }
    //endregion

    //region prevCalcLabel methods

    /**
     * Adds text on to the string of previous calculations
     * @param text the text to be added
     */
    public static void addToPreviousNums(String text)
    {
        prevNums.setText(prevNums.getText()+text);
    }

    /**
     * wipes the text in the previous calculations label
     */
    public static void clearPreviousNums()
    {
        prevNums.setText("");
    }
    //endregion

    /**
     * Helper method that determines the size of rows or columns.
     * To evenly space n number of rows or columns, the total amount
     * of space must be divided by the number of columns for an even spread. This is done
     * by dividing getHeight or getWidth by num for rows or columns respectively. The main
     * purpose of this method is that the row width and column height methods for a
     * gridBagLayout require an array of values containing the values.
     *
     * @param num the number of rows or columns to be sized
     * @param rowsOrColumns determines whether the method creates row or column spacing
     * @return an integer array of values representing the cell row or column sizes
     */
    private static int[] distributeSizes(int num, String rowsOrColumns)
    {
        int[] output = new int[num];
        for(int i = 0; i < num; i++)
        {
            if(rowsOrColumns.equalsIgnoreCase("rows"))
                output[i] = (calcWindow.getHeight() / num);
                if (i == 0)
                    output[i] = output[i] / 2;
                if (i == 1)
                    output[i] = (int) (output[i] * 1.5);
            if(rowsOrColumns.equalsIgnoreCase("columns"))
                output[i] = (calcWindow.getWidth() / num);
        }
        return output;
    }

    /**
     * Method that creates the button objects and returns a hashmap containing
     * references to them so that they can be easily accessed with the
     * HashMap.get("key") method.
     * @param strings the strings to be turned in to buttons
     * @return HashMap of buttons with keys corresponding to their values
     */
    private static Map<String, JButton> initializeButtons(String... strings)
    {
        //Loop through strings and create a hashmap of buttons
        Map<String, JButton> output = new HashMap<>();
        for (String value : strings)
        {
            JButton btn = new JButton(value);
            btn.setForeground(Color.DARK_GRAY);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setRolloverEnabled(true);
            btn.setName(value);

            output.put(value, btn);
        }
        return output;
    }

    /**
     * Initializes the desired traits of the JFrame window,
     * calls the buildGUI method as part of the startup process
     */
    public static void initializeWindow()
    {
        //Set attributes for the window, then call the GUI builder
        calcWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcWindow.setSize(300,550);
        calcWindow.setResizable(false);
        calcWindow.setTitle("Calculator");

        buildGUI();

        calcWindow.setVisible(true);
    }

    /**
     * Hefty method that builds the entire GUI layout. First builds the panel
     * and sets the behavior of the GridBagLayout. Then it proceeds to draw out
     * the buttons by adjusting the c.gridx or c.gridy to move around the grid.
     * Some components take up more than one cell, that's why sometimes the
     * gridWidth or gridHeight value is changed before adding a component and
     * changed back to normal immediately after. Also adds the event listeners
     * to the buttons as they're added
     */
    public static void buildGUI()
    {
        //region Init
        //Initialize the main calculator panel
        JPanel calcPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        calcPanel.setBorder(new EmptyBorder(6, 6, 6, 6));
        calcPanel.setBackground(Color.DARK_GRAY);

        //Call helper method to evenly size rows and columns
        gbl.rowHeights = distributeSizes(8, "rows");
        gbl.columnWidths = distributeSizes(4, "columns");

        calcPanel.setLayout(gbl);

        //Set general grid cell attributes
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(2, 2, 2, 2);

        //endregion

        //region Drawing

        //Row 1
        c.gridy = 0;
        c.gridx = 0;
        c.gridwidth = 4;
        prevNums.setForeground(Color.white);
        prevNums.setEnabled(false);
        prevNums.setHorizontalAlignment(SwingConstants.RIGHT);
        prevNums.setFont(new Font("Arial", Font.PLAIN, 15));
        prevNums.setVerticalAlignment(SwingConstants.BOTTOM);
        calcPanel.add(prevNums, c);

        //Row 2
        c.gridy = 1;
        c.gridx = 0;
        c.gridwidth = 4;

        //Add the number display
        calcDisplay.setEditable(false);
        calcDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
        calcDisplay.setForeground(Color.DARK_GRAY);
        calcDisplay.setFont(new Font("Arial", Font.BOLD, 30));
        calcDisplay.setMargin(new Insets(-12, 0, -12, 0));

        //add to grid
        calcPanel.add(calcDisplay, c);

        c.gridwidth = 1;

        //Row 3
        c.gridy = 2;
        c.gridx = 0;

        //Initialize mini grid of radio buttons then add to cell, add event listeners
        JPanel radioButtons = new JPanel(new GridLayout(2,1));
        radioButtons.setBackground(Color.DARK_GRAY);

        rd1.setText("on");
        rd1.setFont(new Font("Arial", Font.BOLD, 15));
        rd1.setForeground(Color.white);
        rd1.setSelected(true);
        rd1.addActionListener(new btnRdOnClick());
        radioButtons.add(rd1);

        rd2.setText("off");
        rd2.setFont(new Font("Arial", Font.BOLD, 15));
        rd2.setForeground(Color.white);
        rd2.addActionListener(new btnRdOffClick());
        radioButtons.add(rd2);

        calcPanel.add(radioButtons, c);

        //Proceed with row
        c.gridx = 1;
        calcPanel.add(buttons.get("AC"), c);
        buttons.get("AC").addActionListener(new btnACClick());

        c.gridx = 2;
        calcPanel.add(buttons.get("CE"), c);
        buttons.get("CE").addActionListener(new btnCClick());

        c.gridx = 3;
        calcPanel.add(buttons.get("\u00F7"), c);
        buttons.get("\u00F7").addActionListener(new btnDivideClick());

        //Row 4
        c.gridy = 3;

        c.gridx = 0;
        calcPanel.add(buttons.get("\u221A"), c);
        buttons.get("\u221A").addActionListener(new btnSqrtClick());

        c.gridx = 1;
        calcPanel.add(buttons.get("x"+"\u00B2"), c);
        buttons.get("x"+"\u00B2").addActionListener(new btnxSquareClick());

        c.gridx = 2;
        calcPanel.add(buttons.get("1/x"), c);
        buttons.get("1/x").addActionListener(new btnRecipClick());

        c.gridx = 3;
        calcPanel.add(buttons.get("-"), c);
        buttons.get("-").addActionListener(new btnMinusClick());

        //Row 5
        c.gridy = 4;

        c.gridx = 0;
        calcPanel.add(buttons.get("7"), c);
        buttons.get("7").addActionListener(new btn7Click());

        c.gridx = 1;
        calcPanel.add(buttons.get("8"), c);
        buttons.get("8").addActionListener(new btn8Click());

        c.gridx = 2;
        calcPanel.add(buttons.get("9"), c);
        buttons.get("9").addActionListener(new btn9Click());

        c.gridx = 3;
        calcPanel.add(buttons.get("x"), c);
        buttons.get("x").addActionListener(new btnMultiplyClick());

        //Row 6
        c.gridy = 5;
        c.gridx = 0;
        calcPanel.add(buttons.get("4"), c);
        buttons.get("4").addActionListener(new btn4Click());
        c.gridx = 1;
        calcPanel.add(buttons.get("5"), c);
        buttons.get("5").addActionListener(new btn5Click());
        c.gridx = 2;
        calcPanel.add(buttons.get("6"), c);
        buttons.get("6").addActionListener(new btn6Click());
        c.gridx = 3;
        calcPanel.add(buttons.get("+"), c);
        buttons.get("+").addActionListener(new btnAddClick());

        //Row 7
        c.gridy = 6;

        c.gridx = 0;
        calcPanel.add(buttons.get("1"), c);
        buttons.get("1").addActionListener(new btn1Click());

        c.gridx = 1;
        calcPanel.add(buttons.get("2"), c);
        buttons.get("2").addActionListener(new btn2Click());

        c.gridx = 2;
        calcPanel.add(buttons.get("3"), c);
        buttons.get("3").addActionListener(new btn3Click());

        c.gridx = 3;
        c.gridheight = 2;
        calcPanel.add(buttons.get("="), c);
        buttons.get("=").addActionListener(new btnEqualsClick());
        c.gridheight = 1;

        //Row 8
        c.gridy = 7;

        c.gridx = 0;
        c.gridwidth = 2;
        calcPanel.add(buttons.get("0"), c);
        buttons.get("0").addActionListener(new btn0Click());

        c.gridwidth = 1;
        c.gridx = 2;
        calcPanel.add(buttons.get("."), c);
        buttons.get(".").addActionListener(new btnDecimalClick());

        //endregion

        //Add panel to the JFrame window
        calcWindow.add(calcPanel);
    }
    //region Getters/Setters
    public static JRadioButton getRd1()
    {
        return rd1;
    }
    public static JRadioButton getRd2()
    {
        return rd2;
    }
    //endregion
}