package myPackage;
//
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.concurrent.TimeUnit;
//logger from App class
import static myPackage.App.MyLogger;
/**
 * GetXMLupdater gets the data from Israel"s Bank API (in XML format)
 * saves it to local file
*/
public class GetXMLupdater implements Runnable{
    private String FN;
    private int calls;
    //
    public GetXMLupdater(String fn){
        calls = 0;
        FN = fn;
        if(saveFile() == 0){
            MyLogger.error(this.getClass().getName() + " first download failed");
        }
        MyLogger.info(this.getClass().getName() + " with FileName: "+ fn +" object was created");
    }
    //
    private int saveFile(){
        //connection vars
        InputStream inpStream = null;
        HttpsURLConnection connection = null;
        //
        try{
            MyLogger.info("svaeFile() API call starting");
            //
            URL bankAPI_URL = new URL("https://www.boi.org.il/currency.xml");
            connection = (HttpsURLConnection) bankAPI_URL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            //
            inpStream = connection.getInputStream();
            //
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inpStream);
            doc.getDocumentElement().normalize();
            //
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            //
            File currencies = new File("Currencies.xml");
            transformer.transform(new DOMSource(doc), new StreamResult(currencies));
        }
        catch(SAXException | ParserConfigurationException | IOException | TransformerException e){
            e.printStackTrace();
            MyLogger.info("GetXMLupdater failed to get new data in call number " + this.calls);
            return 0;
        }
        finally {
            if(inpStream != null){
                try {
                    inpStream.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                    MyLogger.error("GetXMLupdater function saveFile() error:");
                    MyLogger.error(e);
                    return 0;
                }
            }
            if(connection != null){
                connection.disconnect();
                return 1;
            }
        }
        return 1;
    }
    //
    public void run(){
        while(true){
            try{
                if(saveFile() == 0){
                    MyLogger.info("GetXMLupdater function saveFile failed with call number " + this.calls);
                }
                this.calls++;
                TimeUnit.HOURS.sleep(1);
            }
            catch (InterruptedException e){
                MyLogger.error("GetXMLupdater function saveFile() error:");
                MyLogger.error(e);
                e.printStackTrace();
            }
        }
    }
}
