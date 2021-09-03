package BMIandInterest;




import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class InterestServer extends Application {


    private int clientnr = 0;

    TextArea ta = new TextArea();
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Text area for displaying contents


        // Create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("firstPart.Server"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        new Thread(() -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(9095);
                ta.appendText("MultiThreadServer started at " + new Date() + '\n');




                // Listen for a connection request
                while (true) {

                    Socket socket = serverSocket.accept();
                    clientnr++;

                    Platform.runLater(() ->{
                        ta.appendText("Starting thread for client" + new Date() + '\n');

                        InetAddress inetAddress = socket.getInetAddress();
                        ta.appendText(inetAddress + "\n");
                        System.out.println(inetAddress);});


                    new Thread(new HandleAClient(socket)).start();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
    class HandleAClient implements Runnable {
        private Socket socket;
        public HandleAClient(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // Create data input and output streams
                ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());
                while (true) {
                    Loan loan = (Loan) inputFromClient.readObject();


                    double interest = loan.getLoan() * Math.pow(1 + (loan.getInterest() / loan.getPeriod()), 12 * loan.getPeriod());

                    double monthyPayment = interest/(loan.getPeriod() * 12);

                    CalculatedLoan calculatedLoan = new CalculatedLoan(monthyPayment, interest);
                    outputToClient.writeObject(calculatedLoan);
                    outputToClient.flush();

                    Platform.runLater(() -> {
                        ta.appendText("Total amount to be paid back is "
                                + interest + "over a period of " + loan.getPeriod() + " years, with payments set to " + monthyPayment + '\n');
                    });


                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }



        public static void main(String[] args) {
            launch(args);
        }
    }}


