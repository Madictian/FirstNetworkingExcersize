package Echo;

import firstPart.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {


    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    public void startIt() throws IOException {
        // Panel p to hold the label and text field
        Scanner scanner = new Scanner(System.in);
        // Create a socket to connect to the server
        Socket socket = new Socket("10.200.130.15", 8080);


        // Create an input stream to receive data from the server
        fromServer = new DataInputStream(socket.getInputStream());

        // Create an output stream to send data to the server
        toServer = new DataOutputStream(socket.getOutputStream());
        System.out.println(socket.getLocalPort());
        while (true) {
            try {

                toServer.writeUTF(scanner.next());
                toServer.flush();
                String text = fromServer.readUTF();

                System.out.println(text);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }


        /**
         * The main method is only needed for the IDE with limited
         * JavaFX support. Not needed for running from the command line.
         */

    }
    public static void main (String[]args) throws IOException {
        EchoClient client = new EchoClient();
        client.startIt();

    }
}

