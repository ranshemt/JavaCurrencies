package myPackage;
//
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

//
public class App {
    public static Logger MyLogger;
    //logger configuration
    static {
        String loggerFile = "logs.txt";
        MyLogger = Logger.getLogger(Class.class.getName());
        try{
            PrintWriter writer = new PrintWriter(loggerFile);
            writer.print("");
            writer.close();
            MyLogger.addAppender(new FileAppender(new PatternLayout("%-5p [%t]: %m%n"), loggerFile));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    //private gui
    private CurrenciesGUI myGUI = null;
    //calculator
    private XMLtoList myList = null;
    private GetXMLupdater myUpdater = null;
    //
    //
    public App(){
        //get file from API and save it locally
        //create list from local file
        myList = new XMLtoList();
        //
        myGUI = new CurrenciesGUI();
        //
        MyLogger.info(this.getClass().getName() + " object was created");
    }
    //
    public void runGUI(){
        myGUI.createUI();
        myGUI.updateDate(myList.g_lastUpdate());
        //myGUI.updateCurrencies(myList.g_CurrenciesList());
        //
        Runnable myRunnable = () -> new GetXMLupdater("Currencies.xml");
        //
        try{
            SwingUtilities.invokeAndWait(myRunnable);
            myGUI.updateCurrencies(myList.g_CurrenciesList());
        }
        catch (Exception e) {
            System.out.println("error in runGUI(): " + e);
            MyLogger.info("error in runGUI(): " + e);
            e.printStackTrace();
        }
    }
    //
    public void printData(){
        System.out.println("Last Update: " + myList.g_lastUpdate());
        for(int i = 0; i < myList.g_CurrenciesList().length; i++){
            System.out.println(myList.g_CurrenciesList()[i]);
        }
    }
    //
    public void printSelected(){
        
    }
}
