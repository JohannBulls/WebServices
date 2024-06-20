package edu.escuelaing.arsw.ASE.app;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {

    private List<ChatClient> clientes;

    public ChatServerImpl() throws RemoteException {
        super();
        clientes = new ArrayList<>();
    }

    @Override
    public void registrarCliente(ChatClient cliente) throws RemoteException {
        clientes.add(cliente);
        System.out.println("Cliente registrado: " + cliente.getNombre());
    }

    @Override
    public void enviarMensaje(String mensaje, ChatClient cliente) throws RemoteException {
        System.out.println("Mensaje recibido en el servidor: " + mensaje);
        for (ChatClient c : clientes) {
            if (!c.equals(cliente)) {
                c.recibirMensaje(mensaje);
            }
        }
    }

    public static void main(String[] args) {
        try {
            ChatServerImpl servidor = new ChatServerImpl();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.Naming.rebind("rmi://localhost:1099/ChatServer", servidor);
            System.out.println("Servidor de chat listo...");
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
