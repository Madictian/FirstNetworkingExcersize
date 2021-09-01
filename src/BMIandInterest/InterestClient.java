package BMIandInterest;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class InterestClient {


    public void startIt() throws IOException {
        // Panel p to hold the label and text field
        Scanner scanner = new Scanner(System.in);

        // Create a socket to connect to the server
        Socket socket = new Socket("localhost", 9095);
        // Socket socket = new Socket("130.254.204.36", 8000);
        // Socket socket = new Socket("drake.Armstrong.edu", 8000);

        // Create an input stream to receive data from the server

        ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
        System.out.println(socket.getLocalPort());


        while(true) {

            try {
                System.out.println("Enter loan");
                double loan = scanner.nextDouble();

                System.out.println("Enter interest");
                double interest = scanner.nextDouble();

                System.out.println("Enter period of loan");
                double period = scanner.nextDouble();

                Loan object = new Loan(loan, interest, period);
                toServer.writeObject(object);
                toServer.flush();


                CalculatedLoan loan1 = (CalculatedLoan) fromServer.readObject();
                System.out.println("Monthly payment will be set to " + loan1.getMonthlyPayment() + "\n");
                System.out.println("For a total of " + loan1.getTotalPayment());

            } catch (IOException | ClassNotFoundException ex) {
                System.err.println(ex);
            }
        }
    }



    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) throws IOException {
        InterestClient client = new InterestClient();
        client.startIt();

    }
}
