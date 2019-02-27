package myPackage;
//logger from App class
import static myPackage.App.MyLogger;
//
//specific currency with relevant data from XML
public class Currency {
    private String __NAME, __CURRENCYCODE, __COUNTRY;
    private int __UNIT;
    private double __RATE, __CHANGE;
    //
    public Currency(String name, String code, String country, int unit, double rate, double change){
        boolean useDefault = false;
        if((name == null || name.isEmpty()) || (code == null || code.isEmpty()) || (country == null || country.isEmpty())){
            System.out.println("error creating new Currency, invalid String data received");
            useDefault = true;
        }
        if(unit <= 0 || rate <= 0){
            System.out.println("error creating new Currency, invalid int/double data received");
            useDefault = true;
        }
        if(useDefault == false){
            this.__NAME = name;
            this.__CURRENCYCODE = code;
            this.__COUNTRY = country;
            this.__UNIT = unit;
            this.__RATE = rate;
            this.__CHANGE = change;
        } else{
            this.__NAME = "Error Currency";
            this.__CURRENCYCODE = "ERR";
            this.__COUNTRY = "Error Land";
            this.__UNIT = 0;
            this.__RATE = 0;
            this.__CHANGE = 100;
        }
        System.out.println(name + " Currency created");
    }
    //
    //getters- method name matches XML tag name
    public String NAME(){
        return __NAME;
    }
    public String CURRENCYCODE(){
        return __CURRENCYCODE;
    }
    public String COUNTRY(){
        return __COUNTRY;
    }
    public int UNIT(){
        return __UNIT;
    }
    public double RATE(){
        return __RATE;
    }
    public double CHANGE(){
        return __CHANGE;
    }
    //
    //
    public String toString(){
        String str = "<CURRENCY>";
        str += "   <NAME>\t\t\t" + this.__NAME + "\t\t</NAME>";
        str += "   <UNIT>\t\t\t" + this.__UNIT + "\t\t</UNIT>";
        str += "   <CURRENCYCODE>" + this.__CURRENCYCODE + "\t\t</CURRENCYCODE>";
        str += "   <COUNTRY>\t\t" + this.__COUNTRY + "\t\t</COUNTRY>";
        str += "   <RATE>\t\t\t" + this.__RATE + "\t\t</RATE>";
        str += "   <CHANGE>\t\t" + this.__CHANGE + "\t\t</CHANGE>";
        str += "\n</CURRENCY>";
        return str;
    }
}
