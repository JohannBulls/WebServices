package edu.escuelaing.arsw.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A client program that connects to a server running on localhost port 35000.
 * It sends a number to the server and displays the server's response (the square of the number).
 */
public class NumClient {

    /**
     * Main method that starts the client.
     * @param args Command line arguments (not used).
     * @throws IOException If an I/O error occurs when establishing a connection or communicating with the server.
     */
    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        // Establish connection with the server
        try {
            echoSocket = new Socket("127.0.0.1", 35000); // Connect to server on localhost:35000
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        System.out.print("Enter a number: ");
        // Read user input and send to server, then read and display server's response
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput); // Send user input to server
            String response = in.readLine(); // Receive response from server
            System.out.println("Server response: " + response);
            System.out.print("Enter another number (or 'exit' to quit): ");
            if ("exit".equalsIgnoreCase(userInput.trim())) {
                break; // Exit loop if user types 'exit'
            }
        }

        // Close resources
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
