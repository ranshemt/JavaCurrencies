package myPackage;
//
import org.apache.log4j.Logger;
//
public class App {
    public static Logger MyLogger;
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
        
        System.out.println(this.getClass().getName() + " object was created");
    }
    public static void tryPrint(String val){
        System.out.println("App received: " + val);
    }
}
