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
    private String lastUpdate;
    //
    //constructor
    public XMLtoList(){
        this.CurrenciesList = new Currency[14];
        if(XMLtoArr() == 0){
            MyLogger.error(this.getClass().getName() + " object creation FAILED");    
        }
        else{
            MyLogger.info(this.getClass().getName() + " object was created");
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
            lastUpdate = doc.getElementsByTagName("LAST_UPDATE").item(0).getFirstChild().getNodeValue();
            MyLogger.info("LAST_UPDATE: " + lastUpdate);
            this.rawList = doc.getElementsByTagName("CURRENCY");
        }
        catch(Exception e){
            MyLogger.error("caught error in XMLtoRAW: " + e);
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
        for(int i = 0; i < this.rawList.getLength(); i++){
            String name        =                    this.rawList.item(i).getChildNodes().item(1 ).getTextContent( );
            int    unit        = Integer.parseInt  (this.rawList.item(i).getChildNodes().item(3 ).getTextContent());
            String code        =                    this.rawList.item(i).getChildNodes().item(5 ).getTextContent( );
            String country =                    this.rawList.item(i).getChildNodes().item(7 ).getTextContent( );
            double rate        = Double.parseDouble(this.rawList.item(i).getChildNodes().item(9 ).getTextContent());
            double change      = Double.parseDouble(this.rawList.item(i).getChildNodes().item(11).getTextContent());
            this.CurrenciesList[i] = new Currency(name, code, country, unit, rate, change);
            MyLogger.info("new item in CurrenciesList:");
            MyLogger.info(this.CurrenciesList[i]);
        }
        MyLogger.info("XMLtoArr finished with " +this.CurrenciesList.length+ " items");
        return 1;
    }
    //
    //
    public Currency[] g_CurrenciesList(){
        return this.CurrenciesList;
    }
    public String g_lastUpdate(){
        return this.lastUpdate;
    }
}
