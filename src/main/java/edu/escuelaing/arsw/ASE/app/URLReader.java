package edu.escuelaing.arsw.ASE.app;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Application to read data from a user-provided URL and save it to a file named 'resultado.html'.
 * The file is saved in the directory 'src/main/java/resources/'.
 */
public class URLReader {

    /**
     * Main method where the program execution starts.
     * Prompts the user to enter a URL, reads data from the URL, and saves it to 'resultado.html'.
     * The file is saved in 'src/main/java/resources/' directory.
     * Handles invalid URL inputs by prompting the user again until a valid URL is entered.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the URL: ");
            String urlString = scanner.nextLine().trim();

            try {
                URL url = new URL(urlString);

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    String inputLine;
                    StringBuilder content = new StringBuilder();

                    while ((inputLine = reader.readLine()) != null) {
                        content.append(inputLine);
                        content.append("\n");
                    }

                    String outputPath = "src/main/java/resources/resultado.html";
                    File outputFile = new File(outputPath);

                    outputFile.getParentFile().mkdirs();

                    try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                        fos.write(content.toString().getBytes());
                    }

                    System.out.println("File resultado.html has been saved to: " + outputPath);
                    break;

                } catch (IOException e) {
                    System.err.println("Error reading data from the URL: " + e.getMessage());
                }

            } catch (MalformedURLException e) {
                System.err.println("Invalid URL: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
