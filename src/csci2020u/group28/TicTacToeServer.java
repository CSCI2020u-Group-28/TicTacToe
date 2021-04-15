package csci2020u.group28;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.*;

/**
 * A server for a two-play tic tac toe game.
 */
public class TicTacToeServer extends Frame implements Runnable {

    private String ip = "localhost";
    private ServerSocket serverSocket=null;
    private static int port=8000;
    private int errors = 0;
    private Thread thread;
    private String waitingMsg = "Waiting for another player...";
    private String opponentAfkMsg = "Your opponent has disconnected.";
    private Scanner scanner = new Scanner(System.in);
    private boolean yourTurn = false;
    private Socket socket;

    private boolean waiting = true;
    private boolean opponentAfk=false;
    private boolean accepted= true;
    private DataOutputStream dos;
    private DataInputStream dis;

    public TicTacToeServer (int port) throws IOException {
        System.out.println("Please input the port: ");
        port = scanner.nextInt();
        while (port < 1 || port > 65535) {
            System.out.println("The port you entered was invalid, please input another port: ");
            port = scanner.nextInt();
        }

        TicTacToeBoard.createGameBoard();

        if (!connect()) {
            try {
                serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
            } catch (Exception e) {
                e.printStackTrace();
            }
            yourTurn = true;
        }

        serverSocket= new ServerSocket(port);
        this.port=port;

        thread= new Thread(this, "TicTacToe");
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            check();

            if (!accepted){
                listening();
            }
        }
    }

    public void handleRequests() throws IOException{
        System.out.println("Listening to port: "+ port);

        while (true){
            Socket clientSocket= serverSocket.accept();
            TicTacToeHandler handler = new TicTacToeHandler(clientSocket);
            Thread handlerThread = new Thread(handler);
            handlerThread.start();
        }
    }

    private void listening() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            accepted = true;
            System.out.println("Client's request to join is accepted.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean connect() {
        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            accepted = true;
        } catch (IOException e) {
            System.out.println("Error: unable to connect to the address: " + ip + ":" + port );
            return false;
        }
        System.out.println("Successfully connected to the server.");
        return true;
    }

    public static void main(String[] args) {
        try {
            TicTacToeServer server = new TicTacToeServer(port);
            server.handleRequests();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void check() {
        if (errors >= 10) {
            opponentAfk = true;
            System.out.println(opponentAfkMsg);
        }
        if (!yourTurn && !opponentAfk) {
            //TicTacToeBoard.Tile();
            TicTacToeBoard.checkState();
            yourTurn = true;
        }
        else{
            errors++;
        }
    }
}