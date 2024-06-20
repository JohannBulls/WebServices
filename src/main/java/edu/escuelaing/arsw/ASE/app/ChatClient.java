package edu.escuelaing.arsw.ASE.app;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void recibirMensaje(String mensaje) throws RemoteException;
    void enviarMensaje(String mensaje) throws RemoteException;
}
