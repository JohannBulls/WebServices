package edu.escuelaing.arsw.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple echo server that listens on port 35000 for incoming connections.
 * Upon receiving a number from a client, it computes the square of the number
 * and sends the result back to the client.
 */
public class NumServer {

    /**
     * Main method that starts the server.
     * @param args Command line arguments (not used).
     * @throws IOException If an I/O error occurs when waiting for a connection or receiving data.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        System.out.println("Server started. Waiting for a client...");

        Socket clientSocket = null;

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        System.out.println("Client connected.");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            try {
                int number = Integer.parseInt(inputLine.trim());
                int square = number * number;

                System.out.println("Received: " + number);
                System.out.println("Sending square: " + square);

                out.println(square);
            } catch (NumberFormatException e) {
                out.println("Invalid input. Please send a valid number.");
            }
        }

        // Close resources
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
