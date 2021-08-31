package date;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Dateserver {
    public static void main (String[]args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9095);
        System.out.println("server started!");
        Socket socket = serverSocket.accept();

        DataOutputStream outputDate = new DataOutputStream(socket.getOutputStream());
        DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());

        Date date = new Date();

        outputDate.writeUTF(date.toString());
        outputDate.flush();

        outputDate.close();
        inputFromClient.close();

    }
    }
