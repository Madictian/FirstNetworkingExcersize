package firstPart;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    public void startIt() throws IOException {
        // Panel p to hold the label and text field
        Scanner scanner = new Scanner(System.in);

        try {
            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 9095);


            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());
            System.out.println(socket.getLocalPort());
        } catch (IOException ex) {
            System.out.println(ex);
        }
        while(true) {
            try {
                // Get the radius from the text field
                double radius = Double.parseDouble(scanner.next());

                // Send the radius to the server
                toServer.writeDouble(radius);
                toServer.flush();

                // Get area from the server
                double area = fromServer.readDouble();

                // Display to the text area


            } catch (IOException ex) {
                System.err.println(ex);
            }
        }


    }



    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.startIt();

    }
}
