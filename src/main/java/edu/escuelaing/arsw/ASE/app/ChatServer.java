package edu.escuelaing.arsw.ASE.app;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote {
    void registrarCliente(ChatClient cliente) throws RemoteException;
    void enviarMensaje(String mensaje, ChatClient cliente) throws RemoteException;
}
