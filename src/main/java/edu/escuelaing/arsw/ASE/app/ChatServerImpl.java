package edu.escuelaing.arsw.ASE.app;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {

    private final List<ChatClient> clientes;

    public ChatServerImpl() throws RemoteException {
        super();
        clientes = new ArrayList<>();
    }

    @Override
    public synchronized void registrarCliente(ChatClient cliente) throws RemoteException {
        if (cliente != null) {
            clientes.add(cliente);
            System.out.println("Cliente registrado: " + cliente.getNombre());
        } else {
            System.err.println("Intento de registrar un cliente nulo.");
        }
    }

    @Override
    public synchronized void enviarMensaje(String mensaje, ChatClient cliente) throws RemoteException {
        if (mensaje != null && cliente != null) {
            System.out.println("Mensaje recibido en el servidor: " + mensaje);
            for (ChatClient c : clientes) {
                if (!c.equals(cliente)) {
                    c.recibirMensaje(mensaje);
                }
            }
        } else {
            System.err.println("Mensaje o cliente nulo al intentar enviar mensaje.");
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Iniciando el servidor de chat...");
            ChatServerImpl servidor = new ChatServerImpl();
            try {
                LocateRegistry.createRegistry(1099);
                System.out.println("Registro RMI creado en el puerto 1099.");
            } catch (RemoteException e) {
                System.out.println("Registro RMI ya existente en el puerto 1099.");
            }
            Naming.rebind("rmi://localhost:1099/ChatServer", servidor);
            System.out.println("Servidor de chat listo y registrado en RMI.");
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.toString());
            e.printStackTrace();
        }
    }
}
