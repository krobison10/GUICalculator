package GUICalculator;

import java.time.LocalDateTime;
import java.io.*;
import java.util.Scanner;

public class InputLogger
{
    private static File file;
    private static FileWriter writer;

    private static String firstNumber = "", operation = "", secondNumber = "", result = "", errorMessage = "";
    private static boolean hasErrorMessage = false;

    static void initialize() throws IOException
    {
        try
        {
            file = new File("CalcLog_" +  LocalDateTime.now().toString() + ".txt");
            if(file.createNewFile())
            {
                writer = new FileWriter(file);
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }
    static void addOperationAndResult(String op, String res)
    {
        operation = op;
        result = res;
    }
    static void addFirstNum(String value)
    {
        firstNumber = value;
    }
    static void addSecondNum(String value)
    {
        secondNumber = value;
    }
    static void addErrorMessage(String message)
    {
        errorMessage = message;
        hasErrorMessage = true;
    }
    static void newLog()
    {
        firstNumber = "";
        operation = "";
        secondNumber = "";
        result = "";
        errorMessage = "";

        hasErrorMessage = false;
    }
    static void finish() throws IOException
    {
        writer.close();
        printToConsole();
    }
    static void closeLine() throws IOException
    {
        String line = "";
        line = firstNumber;
        if(operation.length() > 0)
            line += " " + operation;
        if(secondNumber.length() > 0 )
            line += " " + secondNumber;
        if(result.length() > 0)
            line += " = " + result;
        if(hasErrorMessage)
            line += "-Error: " + errorMessage;
        if(line.length() > 0)
        {
            writer.write(line + "\n");
        }
    }
    private static String getFileContents() throws FileNotFoundException
    {
        StringBuilder entireLogString = new StringBuilder();
        Scanner scanner = new Scanner(file);

        while(scanner.hasNext())
        {
            entireLogString.append(scanner.nextLine().trim()).append("\n");
        }
        scanner.close();

        return entireLogString.toString();
    }
    private static void printToConsole() throws FileNotFoundException
    {
        System.out.println("Log file created: " + file.getName() + "\n" + getFileContents());
    }
}
