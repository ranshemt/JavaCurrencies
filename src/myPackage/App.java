package myPackage;
//
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;
import java.io.PrintWriter;

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
    //calculator
    private XMLtoList myList = null;
    private GetXMLupdater myUpdater = null;
    //
    //
    public App(){
        //get file from API and save it locally
        myUpdater = new GetXMLupdater("Currencies.xml");
        //create list from local file
        myList = new XMLtoList();
        //
        MyLogger.info(this.getClass().getName() + " object was created");
    }
    public static void tryPrint(String val){
        System.out.println("App tryPrint() received: " + val);
        MyLogger.info("App tryPrint() received: " + val);
    }
}
