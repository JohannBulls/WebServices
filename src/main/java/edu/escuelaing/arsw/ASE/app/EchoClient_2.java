package edu.escuelaing.arsw.ASE.app;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * EchoClient_2 is a client application that interacts with the EchoServer_2 using RMI.
 */
public class EchoClient_2 {

    /**
     * Executes the echo service provided by the EchoServer_2.
     *
     * @param ipRmiregistry   The IP address of the RMI registry.
     * @param puertoRmiRegistry The port number of the RMI registry.
     * @param nombreServicio  The name of the service to lookup in the RMI registry.
     */
    public void ejecutaServicio(String ipRmiregistry, int puertoRmiRegistry, String nombreServicio) {
        try {
            Registry registry = LocateRegistry.getRegistry(ipRmiregistry, puertoRmiRegistry);
            EchoServer_2 echoServer = (EchoServer_2) registry.lookup(nombreServicio);
            System.out.println(echoServer.echo("Hola como estas?"));
        } catch (Exception e) {
            System.err.println("Hay un problema:");
            e.printStackTrace();
        }
    }

    /**
     * The main method that runs the EchoClient_2.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        EchoClient_2 ec = new EchoClient_2();
        ec.ejecutaServicio("127.0.0.1", 23000, "echoServer_2");
    }
}
