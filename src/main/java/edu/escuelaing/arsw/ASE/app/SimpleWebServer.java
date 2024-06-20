package edu.escuelaing.arsw.ASE.app;
import java.io.*;
import java.net.*;

public class SimpleWebServer {
    private static final int PORT = 35000;
    private static final String SERVER_DIRECTORY = "server_files/";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is listening on port " + PORT);

        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                System.out.println("Client connected: " + clientSocket);

                String inputLine;
                StringBuilder requestBuilder = new StringBuilder();
                while ((inputLine = in.readLine()) != null && !inputLine.isEmpty()) {
                    requestBuilder.append(inputLine).append("\n");
                }

                String request = requestBuilder.toString();
                System.out.println("Received request: \n" + request);

                // Parse HTTP request
                String[] requestLines = request.split("\n");
                if (requestLines.length > 0) {
                    String[] requestLine = requestLines[0].split(" ");
                    if (requestLine.length > 1) {
                        String method = requestLine[0];
                        String path = requestLine[1];
                        if ("GET".equalsIgnoreCase(method)) {
                            handleGetRequest(path, out);
                        } else if ("POST".equalsIgnoreCase(method)) {
                            handlePostRequest(path, in, out);
                        } else {
                            out.println("HTTP/1.1 405 Method Not Allowed\r\n");
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("Error handling client: " + e.getMessage());
            }
        }
    }

    private static void handleGetRequest(String path, PrintWriter out) throws IOException {
        // Remove leading '/' from path
        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        if (path.isEmpty()) {
            path = "index.html"; // Default file
        }

        File file = new File(SERVER_DIRECTORY + path);
        if (file.exists() && !file.isDirectory()) {
            String contentType = getContentType(path);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: " + contentType);
            out.println("Content-Length: " + file.length());
            out.println();

            try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                String fileLine;
                while ((fileLine = fileReader.readLine()) != null) {
                    out.println(fileLine);
                }
            }
        } else {
            out.println("HTTP/1.1 404 Not Found\r\n");
        }
    }

    private static void handlePostRequest(String path, BufferedReader in, PrintWriter out) throws IOException {
        // Remove leading '/' from path
        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        File file = new File(SERVER_DIRECTORY + path);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null && !inputLine.isEmpty()) {
                writer.write(inputLine + "\n");
            }
        }

        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/plain");
        out.println();
        out.println("File uploaded successfully: " + path);
    }

    private static String getContentType(String path) {
        if (path.endsWith(".html") || path.endsWith(".htm")) {
            return "text/html";
        } else if (path.endsWith(".jpg") || path.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (path.endsWith(".png")) {
            return "image/png";
        } else if (path.endsWith(".gif")) {
            return "image/gif";
        } else if (path.endsWith(".css")) {
            return "text/css";
        } else if (path.endsWith(".js")) {
            return "application/javascript";
        } else {
            return "application/octet-stream";
        }
    }
}
