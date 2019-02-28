package myPackage;
//runs the program, the main
public class CurrenciesDemo {
    public static void main(String[] args){
        //
        App myApp = null;
        System.out.println("will create App object");
        //
        myApp = new App();
        //
        //myApp.printData();
        System.out.println("will run GUI");
        myApp.runGUI();
        //
        System.out.println("\n\n\nThank You!\n");
    }
}
