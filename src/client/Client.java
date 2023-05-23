package client;

import resources.Read;

public class Client extends Thread {

    private final String HOST = "localhost";
    private final int PORT = 5000;

    private ClientConnection cc;

    public void startClient() {
       cc = new ClientConnection(PORT, HOST); 
       cc.initializeService();
    }

    @Override
    public void run() {
        String message = "";
        startClient();
        System.out.println("This is the client.");
        
        while (true) {
            System.out.println("Client > ");
            message = Read.string();
            if (message.equals("salir"))
                break;
            cc.sendString(message);
            message = cc.readString();
            System.out.println("Server > " + message);
        }
        cc.closeEverything();
    }
}