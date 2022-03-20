package GUICalculator;

import java.time.LocalDateTime;
import java.io.*;
import java.util.Scanner;

public class InputLogger
{
    private static File file;
    private static FileWriter writer;
    private static String totalContents = "";

    private static String firstNumber = "", operation = "", secondNumber = "", result = "", errorMessage = "";
    private static boolean hasErrorMessage = false;

    static void initialize()
    {
        try
        {
            file = new File("CalcLog_" +  LocalDateTime.now() + ".txt");
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
    static void closeLine()
    {
        String line = firstNumber;
        if(operation.length() > 0)
            line += " " + operation;
        if(secondNumber.length() > 0 )
            line += " " + secondNumber;
        if(result.length() > 0)
            line += " = " + result;
        if(hasErrorMessage)
            line += "->Error: " + errorMessage;
        if(line.length() > 0)
        {
            totalContents += line + "\n";
        }
    }
    static void finish() throws IOException
    {
        writer.write(totalContents);
        writer.close();
        printToConsole();
    }

    private static void printToConsole()
    {
        System.out.println("Log file created: " + file.getName() + "\n" + totalContents);
    }
    /**
     * Deprecated, of course, reading from the file is the file is the correct way to
     * get the contents, but with the structure of my program currently, this method
     * of writing to the file each time doesn't allow the JUnit testing to work, but
     * I left the method here so you can see that I at least knew of the proper way
     * to do it, but save myself a lot of refactoring for little gain
     *
     * @return the contents of the log file
     * @throws FileNotFoundException if there is trouble acessing the file
     */
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
}
