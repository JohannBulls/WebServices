package edu.escuelaing.arsw.ASE.app;

import java.net.*;
import java.io.*;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        System.out.println("Ready to receive connections ...");

        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.println("Client connected: " + clientSocket);

                String inputLine;
                StringBuilder requestBuilder = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    requestBuilder.append(inputLine).append("\n");
                    if (!in.ready()) {
                        break;
                    }
                }

                String request = requestBuilder.toString();
                System.out.println("Received: " + request);

                // Process the HTTP request
                String httpResponse = generateHttpResponse();
                out.println(httpResponse);

            } catch (IOException e) {
                System.out.println("Error handling client: " + e.getMessage());
            }
        }
    }

    private static String generateHttpResponse() {
        String httpResponse = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Title of the document</title>\n"
                + "</head>"
                + "<body>"
                + "My Web Site"
                + "</body>"
                + "</html>";
        return httpResponse;
    }
}
