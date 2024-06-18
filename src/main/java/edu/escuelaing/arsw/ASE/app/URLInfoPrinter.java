package edu.escuelaing.arsw.ASE.app;
import java.net.URL;
import java.util.Scanner;

/**
 * A program that reads a URL from the console input and prints its components using URL class methods.
 */
public class URLInfoPrinter {

    /**
     * Main method where the program execution starts.
     * Prompts the user to enter a URL, reads it from console, and prints its components.
     * Handles invalid input by prompting again until a valid URL is provided.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String urlString = "";

        while (true) {
            System.out.print("Enter the URL: ");
            urlString = scanner.nextLine().trim();

            if (isValidURL(urlString)) {
                break;
            } else {
                System.out.println("Invalid URL. Please enter a valid URL.");
            }
        }

        scanner.close();

        try {
            URL url = new URL(urlString);

            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Authority: " + url.getAuthority());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + url.getPort());
            System.out.println("Path: " + url.getPath());
            System.out.println("Query: " + url.getQuery());
            System.out.println("File: " + url.getFile());
            System.out.println("Reference: " + url.getRef());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a given string is a valid URL.
     * @param urlString The string to check.
     * @return true if the string is a valid URL, false otherwise.
     */
    static boolean isValidURL(String urlString) {
        try {
            new URL(urlString).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
