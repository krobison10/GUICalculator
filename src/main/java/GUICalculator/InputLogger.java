package GUICalculator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class InputLogger
{
    //region Static Fields
    private static File file;
    private static FileWriter writer;

    private static String totalContents = "";
    private static String firstNumber = "", operation = "", secondNumber = "", result = "";
    private static final ArrayList<String> errors = new ArrayList<>();
    //endregion

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
        errors.add(message);
    }
    static void newLog()
    {
        firstNumber = "";
        operation = "";
        secondNumber = "";
        result = "";
        errors.clear();
    }
    static void closeLine()
    {
        StringBuilder line = new StringBuilder(firstNumber);
        if(operation != null && operation.length() > 0)
            line.append(" ").append(operation);
        if(secondNumber != null && secondNumber.length() > 0 )
            line.append(" ").append(secondNumber);
        if(result != null && result.length() > 0)
            line.append(" = ").append(result);
        for(String err : errors)
        {
            line.append("->Error: ").append(err);
        }
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
}
