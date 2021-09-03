package Tictactoe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
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

                        try {
                            new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    new Thread(new HandleASession(player1, player2)).start();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



    }
    class HandleASession implements Runnable, TTTConstants {
        private Socket player1;
        private Socket player2;

        private char[][] cell = new char[3][3];

        private DataInputStream fromPlayer1;
        private DataOutputStream toPlayer1;
        private DataInputStream fromPlayer2;
        private DataOutputStream toPlayer2;

        private boolean continueToPlay = true;

        public HandleASession(Socket player1, Socket player2) {
            this.player1 = player1;
            this.player2 = player2;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cell[i][j] = ' ';
                }
            }
        }

        @Override
        public void run() {
            try{
                fromPlayer1 = new DataInputStream(player1.getInputStream());
                toPlayer1 = new DataOutputStream(player1.getOutputStream());
                fromPlayer2 = new DataInputStream(player2.getInputStream());
                toPlayer2 = new DataOutputStream(player2.getOutputStream());

                toPlayer1.writeInt(1);

                while (true){
                    int row = fromPlayer1.readInt();
                    int column = fromPlayer1.readInt();
                    cell[row][column] = 'X';

                    if (isWon('X')){
                        toPlayer1.writeInt(PLAYER1_Won);
                        toPlayer2.writeInt(PLAYER1_Won);
                        sendMove(toPlayer2, row, column);
                        break;
                    }else if(isFull()){
                        toPlayer1.writeInt(Draw);
                        toPlayer2.writeInt(Draw);
                        sendMove(toPlayer2, row, column);
                        break;
                    }else{
                        toPlayer2.writeInt(Continue);
                        sendMove(toPlayer2,row,column);
                    }

                    row = fromPlayer2.readInt();
                    column = fromPlayer2.readInt();
                    cell[row][column] = 'O';

                    if (isWon('O')) {
                        toPlayer1.writeInt(PLAYER2_Won);
                        toPlayer2.writeInt(PLAYER2_Won);
                        sendMove(toPlayer1, row, column);
                        break;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void sendMove(DataOutputStream out, int row, int column) throws IOException {
            out.writeInt(row);
            out.writeInt(column);

        }

        private boolean isFull(){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                        if (cell[i][j] == ' '){
                            return false;
                        }}}
            return true;
        }

        private boolean isWon(char token){
            for (int i = 0; i < 3; i++) {
                if((cell[i][0] == token) && (cell[i][1] == token) && (cell[i][2] == token)){ return true;}}

            for (int j = 0; j < 3; j++) {
                if((cell[0][j] == token) && (cell[1][j] == token) && (cell[2][j] == token)){ return true;} }

            if((cell[0][2] == token) && (cell[1][1] == token) && (cell[2][0] == token)){ return true;}

            if((cell[0][0] == token) && (cell[1][1] == token) && (cell[2][2] == token)){ return true;}


            return false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
