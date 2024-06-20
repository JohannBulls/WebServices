package edu.escuelaing.arsw.ASE.app;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class EchoServerImpl_2 implements EchoServer_2 {

    public EchoServerImpl_2(String ipRMIregistry, int puertoRMIregistry, String nombreDePublicacion) {
        try {
            EchoServer_2 echoServer = (EchoServer_2) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry(ipRMIregistry, puertoRMIregistry);
            registry.rebind(nombreDePublicacion, echoServer);
            System.out.println("Echo server ready...");
        } catch (Exception e) {
            System.err.println("Echo server exception:");
            e.printStackTrace();
        }
    }

    @Override
    public String echo(String cadena) throws RemoteException {
        return "desde el servidor: " + cadena;
    }

    public static void main(String[] args) {
        EchoServerImpl_2 ec = new EchoServerImpl_2("127.0.0.1", 23000, "echoServer_2");
    }
}
