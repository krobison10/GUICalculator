package GUICalculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 0.1
 * @author Kyler Robison
 */

public class CalculatorMain
{

    private static final JFrame calcWindow = new JFrame();

    public static void main(String[] args)
    {
        EventQueue.invokeLater(CalculatorMain::initializeWindow);



    }
    public static void buildGUI()
    {
        JPanel calcPanel = new JPanel();
        calcPanel.setLayout(new GridBagLayout());

        //Button set
        Map<String, JButton> buttons = initializeButtons("0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
        "C", "DEL", "\u00F7", "-", "X", "+", "=", ".", "\u221A", ("X"+"\u00B2"), "1/x");

        GridBagConstraints c = new GridBagConstraints();

        //Number Buttons
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;


        //Row 1
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        var calcLabel = new JTextField( 20);
        calcLabel.setEnabled(false);

        calcPanel.add(calcLabel, c);
        c.gridwidth = 1;

        //Row 2
        c.gridy = 1;
        c.gridx = 0;
        //radio buttons
        c.gridx = 1;
        calcPanel.add(buttons.get("C"), c);
        c.gridx = 2;
        calcPanel.add(buttons.get("DEL"), c);
        c.gridx = 3;
        calcPanel.add(buttons.get("\u00F7"), c);

        //Row 3
        c.gridy = 2;
        c.gridx = 0;
        calcPanel.add(buttons.get("\u221A"), c);
        c.gridx = 1;
        calcPanel.add(buttons.get("X"+"\u00B2"), c);
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
        calcPanel.add(buttons.get("X"), c);

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



        //Add parent panel to the JFrame window
        calcWindow.add(calcPanel);
    }
    private static Map<String, JButton> initializeButtons(String... strings)
    {
        Map<String, JButton> output = new HashMap<>();
        for (String value : strings)
            output.put(value, new JButton(value));
        return output;
    }
    public static void initializeWindow()
    {
        calcWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcWindow.setSize(300,500);
        calcWindow.setResizable(false);
        calcWindow.setTitle("Calculator");

        buildGUI();
        calcWindow.setVisible(true);
    }
}