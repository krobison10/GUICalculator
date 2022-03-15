package GUICalculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;

/**
 * @version 1.0 - does nothing but looks pretty
 * @author Kyler Robison
 */

public class CalculatorMain
{
    //declare main window JFrame
    private static final JFrame calcWindow = new JFrame();
    private static final int FONT_SIZE = 20;

    public static void main(String[] args)
    {
        EventQueue.invokeLater(CalculatorMain::initializeWindow);
    }
    public static void buildGUI()
    {
        //Initialize the main calculator panel
        JPanel calcPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.rowHeights = distributeSizes(calcWindow, 7, "rows");
        gbl.columnWidths = distributeSizes(calcWindow, 4, "columns");
        calcPanel.setLayout(gbl);
        calcPanel.setBorder(new EmptyBorder(6, 6, 6, 6));
        calcPanel.setBackground(Color.DARK_GRAY);

        //Define set containing the buttons
        Map<String, JButton> buttons = initializeButtons("0", "1", "2", "3", "4", "5", "6", "7",
        "8", "9", "C", "CE", "\u00F7", "-", "x", "+", "=", ".", "\u221A", ("x"+"\u00B2"), "1/x");

        //Set general grid cell attributes
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        //c.fill = GridBagConstraints.BOTH;

        c.insets = new Insets(2, 2, 2, 2);


        //Row 1
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;

        //Set up label
        var calcLabel = new JTextField("Hello World!");
        calcLabel.setEditable(false);
        calcLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        calcLabel.setFont(new Font("Arial", Font.BOLD, 30));
        calcLabel.setMargin(new Insets(-8, 0, -8, 0));

        //add to grid
        calcPanel.add(calcLabel, c);
        c.gridwidth = 1;

        //Row 2
        c.gridy = 1;
        c.gridx = 0;

        //Initialize mini grid of radio buttons then add to cell
        JPanel radioButtons = new JPanel(new GridLayout(2,1));
        radioButtons.setBackground(Color.DARK_GRAY);

        JRadioButton rd1 = new JRadioButton("on");
        rd1.setFont(new Font("Arial", Font.BOLD, 15));
        rd1.setForeground(Color.white);
        rd1.setSelected(true);
        radioButtons.add(rd1);

        JRadioButton rd2 = new JRadioButton("off");
        rd2.setFont(new Font("Arial", Font.BOLD, 15));
        rd2.setForeground(Color.white);
        radioButtons.add(rd2);

        calcPanel.add(radioButtons, c);

        //Proceed with row
        c.gridx = 1;
        calcPanel.add(buttons.get("C"), c);
        c.gridx = 2;
        calcPanel.add(buttons.get("CE"), c);
        c.gridx = 3;
        calcPanel.add(buttons.get("\u00F7"), c);

        //Row 3
        c.gridy = 2;
        c.gridx = 0;
        calcPanel.add(buttons.get("\u221A"), c);
        c.gridx = 1;
        calcPanel.add(buttons.get("x"+"\u00B2"), c);
        c.gridx = 2;
        calcPanel.add(buttons.get("1/x"), c);
        c.gridx = 3;
        calcPanel.add(buttons.get("-"), c);

        //Row 4
        c.gridy = 3;
        c.gridx = 0;
        calcPanel.add(buttons.get("7"), c);
        c.gridx = 1;
        calcPanel.add(buttons.get("8"), c);
        c.gridx = 2;
        calcPanel.add(buttons.get("9"), c);
        c.gridx = 3;
        calcPanel.add(buttons.get("x"), c);

        //Row 5
        c.gridy = 4;
        c.gridx = 0;
        calcPanel.add(buttons.get("4"), c);
        c.gridx = 1;
        calcPanel.add(buttons.get("5"), c);
        c.gridx = 2;
        calcPanel.add(buttons.get("6"), c);
        c.gridx = 3;
        calcPanel.add(buttons.get("+"), c);

        //Row 6
        c.gridy = 5;

        c.gridx = 0;
        calcPanel.add(buttons.get("1"), c);
        c.gridx = 1;
        calcPanel.add(buttons.get("2"), c);
        c.gridx = 2;
        calcPanel.add(buttons.get("3"), c);
        c.gridx = 3;
        c.gridheight = 2;
        calcPanel.add(buttons.get("="), c);
        c.gridheight = 1;

        //Row 7
        c.gridy = 6;

        c.gridx = 0;
        c.gridwidth = 2;
        calcPanel.add(buttons.get("0"), c);
        c.gridwidth = 1;
        c.gridx = 2;
        calcPanel.add(buttons.get("."), c);

        //Add panel to the JFrame window
        calcWindow.add(calcPanel);
    }
    private static int[] distributeSizes(JFrame calcWindow, int num, String rowsOrColumns)
    {
        int[] output = new int[num];
        for(int i = 0; i < num; i++)
        {
            if(rowsOrColumns.equalsIgnoreCase("rows"))
                output[i] = (calcWindow.getHeight() / num);
            if(rowsOrColumns.equalsIgnoreCase("columns"))
                output[i] = (calcWindow.getWidth() / num);
        }
        return output;
    }
    private static Map<String, JButton> initializeButtons(String... strings)
    {
        //Loop through strings and create a hashmap of buttons
        Map<String, JButton> output = new HashMap<>();
        for (String value : strings)
        {
            JButton btn = new JButton(value);
            btn.setForeground(Color.DARK_GRAY);
            btn.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
            btn.setRolloverEnabled(true);
            output.put(value, btn);
        }
        return output;
    }
    public static void initializeWindow()
    {
        //Set attributes for the window, then call the GUI builder
        calcWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcWindow.setSize(300,500);
        calcWindow.setResizable(false);
        calcWindow.setTitle("Calculator");
        buildGUI();
        calcWindow.setVisible(true);
    }
}