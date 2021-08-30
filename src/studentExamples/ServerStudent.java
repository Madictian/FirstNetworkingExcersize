package studentExamples;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStudent {
    private ObjectOutputStream outputToFile;
    private ObjectInputStream inputFromClient;

    public ServerStudent() {
        try{
            ServerSocket serverSocket = new ServerSocket(9095);
            System.out.println("server started!");

            outputToFile = new ObjectOutputStream(new FileOutputStream("studentExamples.Student.dat", true));

            while (true) {
                Socket socket = serverSocket.accept();

                inputFromClient = new ObjectInputStream(socket.getInputStream());

                Object object = inputFromClient.readObject();

                outputToFile.writeObject(object);
                System.out.println("A new student object is stored");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IOException ie){
            ie.printStackTrace();
        }finally {
            try {
                inputFromClient.close();
                outputToFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ServerStudent();
    }
}
