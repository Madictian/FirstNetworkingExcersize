package ip;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Ipserver {


    public static void main (String[]args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(9095);
            System.out.println("server started!");
            Socket socket = serverSocket.accept();

            DataOutputStream outputDate = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());

            InetAddress inetAddress = InetAddress.getByName("localhost");
            System.out.println(inetAddress);


    }
}