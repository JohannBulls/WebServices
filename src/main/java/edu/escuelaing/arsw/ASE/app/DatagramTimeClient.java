package edu.escuelaing.arsw.ASE.app;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeClient {

    public static void main(String[] args) {
        byte[] sendBuf = new byte[256];
        DatagramSocket socket = null;
        InetAddress address = null;
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        String lastReceivedTime = "No data received yet.";

        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("127.0.0.1");
        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            try {
                packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);

                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                lastReceivedTime = received;
                System.out.println("Date: " + received);

            } catch (IOException ex) {
                System.out.println("Failed to receive data. Keeping last known time: " + lastReceivedTime);
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Thread.sleep(5000);  // Espera 5 segundos antes de intentar nuevamente
            } catch (InterruptedException ex) {
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}