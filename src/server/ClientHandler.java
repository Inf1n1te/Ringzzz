package server;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ClientHandler extends Thread {

    private final Socket socket;


    public ClientHandler(Socket socket) {
        super("client-handler-"+ new Random().nextInt());
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            connection: while (true) {
                if (scanner.hasNextLine()) {
                    String[] input = scanner.nextLine().split(" ");
                    switch (input[0]) {
                        case "exit":
                            writer.println("Goodbye");
                            break connection;
                    }
                }
            }

            writer.flush();
            writer.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
