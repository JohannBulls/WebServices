package edu.escuelaing.arsw.ASE.app;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EchoClient_2 {

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

    public static void main(String[] args) {
        EchoClient_2 ec = new EchoClient_2();
        ec.ejecutaServicio("127.0.0.1", 23000, "echoServer_2");
    }
}
