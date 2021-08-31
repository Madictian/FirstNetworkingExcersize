package ip;

import Echo.EchoClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Ipclient {

    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    public void startIt() throws IOException {
        // Panel p to hold the label and text field
        // Create a socket to connect to the server
        Socket socket = new Socket("localhost", 9095);


        // Create an input stream to receive data from the server
        fromServer = new DataInputStream(socket.getInputStream());

        // Create an output stream to send data to the server
        toServer = new DataOutputStream(socket.getOutputStream());
        System.out.println(socket.getLocalPort());



        /**
         * The main method is only needed for the IDE with limited
         * JavaFX support. Not needed for running from the command line.
         */

    }
    public static void main (String[]args) throws IOException {
        Ipclient client = new Ipclient();
        client.startIt();

    }
}
