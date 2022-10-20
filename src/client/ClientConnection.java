package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection {

    private Socket socket;
    // read data from the outside world (iugh)
    private DataOutputStream send;
    // sends data to the outside world :)
    private DataInputStream read;

    /**send the data.
     * 
     */
    public void sendString(String pMessage) {
        try {
            send = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     */
    public String readString() {
        String inputString = "!";
        try {
            read = new DataInputStream(socket.getInputStream());
            inputString = read.readUTF();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return inputString;
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
        try {
            socket = new Socket("", 5);
            read = new DataInputStream(socket.getInputStream());
            send = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
