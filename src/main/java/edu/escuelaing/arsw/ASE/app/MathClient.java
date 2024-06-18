package edu.escuelaing.arsw.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class represents a client that connects to a server and sends numbers to calculate their squares.
 */
public class MathClient {

    /**
     * Main method to run the MathClient.
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int portNumber = 35000;

        try (
                Socket echoSocket = new Socket(serverAddress, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Connected to server. Enter numbers to get their squares:");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                String response = in.readLine();
                System.out.println("Server response: " + response);
            }
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}
