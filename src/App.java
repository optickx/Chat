import java.util.ResourceBundle;

import client.Client;
import server.Server;

public class App {

    private static String rb = 
        ResourceBundle
            .getBundle("resources.connect")
                .getString("TYPE")
                    .toUpperCase();
                    
    public static void main(String[] args) {
        switch (rb) {
            case "CLIENT" : new Client().start();
                break;
            case "SERVER" : new Server().start();
                break;
            default : System.out.println("Invalid type.");
                break; 
        }
    }
}