package edu.escuelaing.arsw.ASE.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The MathServer class represents a server that performs mathematical operations based on client input.
 * It listens on a specified port and handles client connections.
 */
public class MathServer {

    private static final int PORT = 35000; // Port number for the server
    private static String currentOperation = "cos"; // Default operation

    /**
     * Main method to run the MathServer.
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("MathServer is running and listening on port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("Client connected: " + clientSocket);

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Received from client: " + inputLine);
                        String response = processInput(inputLine);
                        System.out.println("Sending response: " + response);
                        out.println(response);
                    }
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            System.exit(-1);
        }
    }

    /**
     * Processes the input received from the client.
     * @param input The input string received from the client.
     * @return The response string to be sent back to the client.
     */
    private static String processInput(String input) {
        try {
            if (input.startsWith("fun:")) {
                String operation = input.substring(4);
                switch (operation) {
                    case "sin":
                    case "cos":
                    case "tan":
                        currentOperation = operation;
                        return "Switched to operation: " + operation;
                    default:
                        return "Unknown operation: " + operation;
                }
            } else {
                double number = Double.parseDouble(input);
                double result;
                switch (currentOperation) {
                    case "sin":
                        result = Math.sin(number);
                        break;
                    case "cos":
                        result = Math.cos(number);
                        break;
                    case "tan":
                        result = Math.tan(number);
                        break;
                    default:
                        return "Invalid operation: " + currentOperation;
                }
                return String.valueOf(result);
            }
        } catch (NumberFormatException e) {
            return "Invalid input: not a number";
        }
    }
}
