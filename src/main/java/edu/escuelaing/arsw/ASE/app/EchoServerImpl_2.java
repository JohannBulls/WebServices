package edu.escuelaing.arsw.ASE.app;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class EchoServerImpl_2 implements EchoServer_2 {

    public EchoServerImpl_2(String ipRMIregistry, int puertoRMIregistry, String nombreDePublicacion) {
        if (ipRMIregistry == null || ipRMIregistry.isEmpty()) {
            throw new IllegalArgumentException("La IP del registro RMI no puede ser nula o vacía");
        }
        if (nombreDePublicacion == null || nombreDePublicacion.isEmpty()) {
            throw new IllegalArgumentException("El nombre de publicación no puede ser nulo o vacío");
        }
        if (puertoRMIregistry <= 0) {
            throw new IllegalArgumentException("El puerto del registro RMI debe ser un valor positivo");
        }
        
        try {
            EchoServer_2 echoServer = (EchoServer_2) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry(ipRMIregistry, puertoRMIregistry);
            registry.rebind(nombreDePublicacion, echoServer);
            System.out.println("Echo server ready...");
        } catch (RemoteException e) {
            System.err.println("Echo server exception: Problema con la conexión RMI");
            e.printStackTrace();
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
        if (args.length != 3) {
            System.err.println("Uso: java EchoServerImpl_2 <IP_RMI_registry> <puerto_RMI_registry> <nombre_de_publicacion>");
            System.exit(1);
        }
        String ipRMIregistry = args[0];
        int puertoRMIregistry = Integer.parseInt(args[1]);
        String nombreDePublicacion = args[2];

        new EchoServerImpl_2(ipRMIregistry, puertoRMIregistry, nombreDePublicacion);
    }
}
