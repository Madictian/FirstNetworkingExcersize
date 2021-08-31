package firstPart;

/*import java.io.*;
import java.net.*;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application {

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
                 DataInputStream inputFromClient = new DataInputStream(
                         socket.getInputStream());
                 DataOutputStream outputToClient = new DataOutputStream(
                         socket.getOutputStream());
                 while (true) {
                     // Receive radius from the client
                     double radius = inputFromClient.readDouble();

                     // Compute area
                     double area = radius * radius * Math.PI;

                     // Send area back to the client
                     outputToClient.writeDouble(area);

                     Platform.runLater(() -> {
                         ta.appendText("Radius received from client: "
                                 + radius + '\n');
                         ta.appendText("Area is: " + area + '\n');
                     });
                 }
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
         }



    public static void main(String[] args) {
        launch(args);
    }
}}*/