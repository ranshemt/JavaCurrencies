package myPackage;
//runs the program, the main
public class CurrenciesDemo {
    public static void main(String[] args){
        App myApp = null;
        System.out.println("will create App object");
        myApp = new App();
        App.tryPrint("some value App will print");
        System.out.println("\n\n\n\n!!!!!\nmain finished!\n\n");
    }
}
