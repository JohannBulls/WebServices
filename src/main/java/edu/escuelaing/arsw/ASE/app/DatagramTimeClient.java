package edu.escuelaing.arsw.ASE.app;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple DatagramTimeClient that sends a request to the DatagramTimeServer and receives the current time.
 */
public class DatagramTimeClient {

    /**
     * The main method that runs the DatagramTimeClient.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        byte[] sendBuf = new byte[256];
        DatagramSocket socket = null;
        InetAddress address = null;
        byte[] buf = new byte[256];
        DatagramPacket packet;
        String lastReceivedTime = "No data received yet.";

        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("127.0.0.1");
        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, "Socket creation failed", ex);
            System.exit(1);  // Exit if socket creation fails
        } catch (UnknownHostException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, "Unknown host", ex);
            System.exit(1);  // Exit if the host is unknown
        }

        while (true) {
            try {
                // Clear buffer
                sendBuf = "REQUEST TIME".getBytes();
                packet = new DatagramPacket(sendBuf, sendBuf.length, address, 4445);
                socket.send(packet);

                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                lastReceivedTime = received;
                System.out.println("Date: " + received);

            } catch (IOException ex) {
                System.out.println("Failed to receive data. Keeping last known time: " + lastReceivedTime);
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, "Data reception failed", ex);
            }

            try {
                Thread.sleep(5000);  // Wait 5 seconds before next attempt
            } catch (InterruptedException ex) {
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, "Sleep interrupted", ex);
            }
        }
    }
}
