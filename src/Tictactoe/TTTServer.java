/*
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TTTServer extends Application implements TTTConstants {

    private int sessionNo = 1;


    @Override
    public void start(Stage primaryStage) {
        TextArea log = new TextArea();

        Scene scene = new Scene(new ScrollPane(log),450,200);

        primaryStage.setTitle("DopeTTT");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread( () -> {
            try {
                ServerSocket serverSocket = new ServerSocket(9876);
                Platform.runLater(() -> log.appendText(new Date() + ": wait for players to join session " + sessionNo + "\n"));

                while(true) {
                    Socket player1 = serverSocket.accept();
                    Platform.runLater(() -> {
                        log.appendText(new Date() + ": Player 1 has joined session " + sessionNo + "\n");
                        log.appendText("Player 1's ip address" + player1.getInetAddress().getHostAddress() + "\n");
                    });

                    new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);

                    Socket player2 = serverSocket.accept();
                    Platform.runLater(() -> {
                        log.appendText(new Date() + ": Player 2 has joined session " + sessionNo + "\n");
                        log.appendText("Player 2's ip address" + player2.getInetAddress().getHostAddress() + "\n");

                        new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER2);
                    });

                    new Thread(new HandleASession(player1, player2)).start();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/