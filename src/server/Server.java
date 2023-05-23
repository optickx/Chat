package server;

import resources.Read;

public class Server extends Thread {

    private final int PORT = 5000;

    private ServerConnection sc;

    public void startServer() {
        sc = new ServerConnection(PORT);
        sc.initializeService();
    }

    
   @Override
   public void run() {
        String message = "";
        startServer();
        System.out.println("This is the server.");
        
        while (true) {
            System.out.println("Server > ");
            message = sc.readString();
            if (message.equals("salir"))
                break; 
            System.out.println("Client > " + message);
            message = Read.string();
            if (message.equals("salir"))
                break;
            sc.sendString(message);
        }
        sc.closeEverything();
   } 
}