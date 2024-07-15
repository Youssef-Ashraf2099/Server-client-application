import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String argv[]) throws Exception {
        String serverIP = "your ip address"; // Replace with the actual server's IPv4 address
        int portNumber = 7010; // Specify the port number

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // Prompt user to type 'CONNECT' to establish connection
        System.out.println("Type 'CONNECT' to connect to the server:");
        String connectCommand = inFromUser.readLine();

        // Check if user typed 'CONNECT'
        if (connectCommand.equalsIgnoreCase("CONNECT")) {
            // Connect to the server
            Socket clientSocket = new Socket(serverIP, portNumber);
            System.out.println("Connected to server.");

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Thread to listen for messages from the server
            Thread listenThread = new Thread(() -> {
                try {
                    String modifiedSentence;
                    while ((modifiedSentence = inFromServer.readLine()) != null) {
                        System.out.println("Server: " + modifiedSentence);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed by server.");
                }
            });

            listenThread.start();

            // Main loop to send messages to the server
            while (true) {
                // Prompt user to enter a message
                System.out.print("You: ");
                String sentence = inFromUser.readLine();

                // Send the message to the server
                outToServer.writeBytes(sentence + '\n');

                // Check if the user wants to quit
                if (sentence.equalsIgnoreCase("QUIT")) {
                    System.out.println("Connection terminated.");
                    break;
                }
            }

            // Close resources
            clientSocket.close();
        } else {
            System.out.println("Invalid command. Exiting...");
        }
    }
}
