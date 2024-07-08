package edu.escuelaing.arsw.ASE.app;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple DatagramTimeServer that listens for requests and responds with the current date and time.
 */
public class DatagramTimeServer {
    private DatagramSocket socket;

    /**
     * Constructs a DatagramTimeServer and binds it to port 4445.
     */
    public DatagramTimeServer() {
        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, "Socket creation failed", ex);
            System.exit(1);  // Exit if socket creation fails
        }
    }

    /**
     * Starts the DatagramTimeServer to listen for incoming requests and respond with the current date and time.
     */
    public void startServer() {
        byte[] buf = new byte[256];
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String dString = new Date().toString();
                buf = dString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);

                System.out.println("Received and responded to request from " + address + ":" + port);

            } catch (IOException ex) {
                Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, "IO exception", ex);
            }
        }
    }

    /**
     * The main method that runs the DatagramTimeServer.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        DatagramTimeServer ds = new DatagramTimeServer();
        ds.startServer();
    }
}
