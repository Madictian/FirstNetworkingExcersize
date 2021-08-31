package Echo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {



    public static void main(String[] args) {
        new Thread(() -> {
            try{
                ServerSocket serverSocket = new ServerSocket(9095);
                System.out.println("server started!");
                Socket socket = serverSocket.accept();

                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());

                DataOutputStream outputEcho = new DataOutputStream(socket.getOutputStream());

                while (true) {


                    String bla = inputFromClient.readUTF();
                    System.out.println(bla + " confirmed");
                    outputEcho.writeUTF(bla);
                    outputEcho.flush();
                    System.out.println(bla + " soutet");

                }
            } catch (IOException ie){
                ie.printStackTrace();

            }
        }).start();
    }
}

