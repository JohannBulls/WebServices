package edu.escuelaing.arsw.ASE.app;

import java.net.*;
import java.io.*;

/**
 * A simple EchoServer that listens on port 35000 and echoes back any received messages.
 */
public class EchoServer {

    /**
     * The main method that runs the EchoServer.
     *
     * @param args Command line arguments (not used).
     * @throws IOException If an I/O error occurs when opening the socket.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Message: " + inputLine);
            outputLine = "Response: " + inputLine;
            out.println(outputLine);
            if (outputLine.equals("Response: Bye."))
                break;
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
