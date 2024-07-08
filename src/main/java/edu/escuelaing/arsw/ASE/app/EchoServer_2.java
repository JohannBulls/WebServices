package edu.escuelaing.arsw.ASE.app;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * EchoServer_2 is a remote interface that defines a method for echoing a string.
 */
public interface EchoServer_2 extends Remote {
    /**
     * Echoes the given string.
     *
     * @param cadena The string to be echoed.
     * @return The echoed string.
     * @throws RemoteException If a remote communication error occurs.
     */
    public String echo(String cadena) throws RemoteException;
}
