package myPackage;
//

//logger from App class
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import static myPackage.App.MyLogger;
/**
 * XMLParser gets the data from local file
 * Parses the file into a Currency[] and returns it
*/
public class XMLtoList{
    private NodeList rawList;
    private Currency[] CurrenciesList;
    //
    //constructor
    public XMLtoList(){
        if(XMLtoArr() == 0){
            System.out.println(this.getClass().getName() + " object creation FAILED");    
        }
        else{
            System.out.println(this.getClass().getName() + " object was created");
        }
    }
    //
    //
    private int XMLtoRAW(){
        File rawFile = new File("Currencies.xml");

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(rawFile);
            doc.getDocumentElement().normalize();
            this.rawList = doc.getElementsByTagName("CURRENCY");
            System.out.println("rawList length = " + this.rawList.getLength());
        }
        catch(Exception e){
            System.out.println("caught error in XMLtoRAW: " + e);
            return 0;
        }
        return 1;
    }
    //
    //XMLtoArr
    private int XMLtoArr(){
        if(XMLtoRAW() == 0){
            return 0;
        }
        else {
            return 1;
        }
    }
}
