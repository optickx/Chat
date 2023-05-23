package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection {
    
    private final int PORT;

    private Socket socket;
    private ServerSocket serverSocket;
    private DataOutputStream send;
    private DataInputStream read;

    public ServerConnection(int pPort) {
        PORT = pPort;
    }

    public void sendString(String pMessage) {
        try {
            send.writeUTF(pMessage);
        } catch (IOException ioe) {
            print("There was an error sending the message (server)");
        }
    }

    public String readString() {
        try {
            return read.readUTF();
        } catch (Exception e) {
            print("There was an error receiving the message (server)");
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

    public void initializeService() {
        print("Waiting for the connection");
        try {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();
                print("Connection succesful.");
            read = new DataInputStream(socket.getInputStream());
            send = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            print("There was an error with the connection on server.");
        }
    }

    public void print(String pString) {
        System.out.println(pString);
    }
}
