import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String argv[]) throws Exception {
        int portNumber = 7010; // Specify the port number

        // Create server socket
        ServerSocket welcomeSocket = new ServerSocket(portNumber);
        System.out.println("Server is running and waiting for a client to connect...");

        // Create BufferedReader to read input from the console
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // Accept client connection
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Client connected.");

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            // Thread to listen for messages from the client
            Thread listenThread = new Thread(() -> {
                try {
                    String clientSentence;
                    while ((clientSentence = inFromClient.readLine()) != null) {
                        System.out.println("Client: " + clientSentence);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed by client.");
                }
            });

            // Thread to send messages to the client
            Thread sendThread = new Thread(() -> {
                try {
                    String serverSentence;
                    while ((serverSentence = inFromServer.readLine()) != null) {
                        outToClient.writeBytes(serverSentence + '\n');
                    }
                } catch (IOException e) {
                    System.out.println("Error sending message to client.");
                }
            });

            listenThread.start();
            sendThread.start();
        }
    }
}