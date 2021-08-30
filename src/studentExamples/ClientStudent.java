package studentExamples;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientStudent {
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;

    Scanner scanner = new Scanner(System.in);

    private void runner() {
        try {
            Socket socket = new Socket("localhost", 9095);

            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Enter thy name");
            name = scanner.next();

            System.out.println("Enther thy street");
            street = scanner.next();

            System.out.println("From which city do thy haiL?");
            city = scanner.next();

            System.out.println("And which state?");
            state = scanner.next();

            System.out.println("What's yall zip code?");
            zip = scanner.next();

            Student student =
                    new Student(name, street, city, street, zip);
            toServer.writeObject(student);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientStudent clientStudent = new ClientStudent();
        clientStudent.runner();
    }
}
