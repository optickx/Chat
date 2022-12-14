package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection {

    private final int PORT;
    private final String MACHINE;

    private Socket socket;
    // read data from the outside world (iugh)
    private DataOutputStream send;
    // sends data to the outside world :)
    private DataInputStream read;

    public ClientConnection(int pPort, String pMachine) {
        PORT = pPort;
        MACHINE = pMachine;
    }

    /**
     * send the data.
     * 
     */
    public void sendString(String pMessage) {
        try {
            send.writeUTF(pMessage);
        } catch (IOException ioe) {
            print("There was an error sending the message (client)");
        }
    }

    /**
     */
    public String readString() {
        try {
            return read.readUTF();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return "!";
    }

    public void closeEverything() {
        try {
            send.close();
            read.close();
            socket.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // opens the connection to
    public void initializeService() {
        print("Waiting for the connection.");
        try {
            socket = new Socket(MACHINE, PORT);
            print("Connection successful.");
            read = new DataInputStream(socket.getInputStream());
            send = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            print("There was an error with the connection on client.");
        }
    }

    public void print(String pString) {
        System.out.println(pString);
    }
}