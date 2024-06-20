package edu.escuelaing.arsw.ASE.app;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {

    private ChatServer servidor;
    private String nombre;

    protected ChatClientImpl(ChatServer servidor, String nombre) throws RemoteException {
        super();
        this.servidor = servidor;
        this.nombre = nombre;
        registrarConServidor();
    }

    private void registrarConServidor() {
        try {
            servidor.registrarCliente(this);
            System.out.println("Conectado al servidor como '" + nombre + "'.");
        } catch (RemoteException e) {
            System.err.println("Error al registrar con el servidor: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void recibirMensaje(String mensaje) throws RemoteException {
        System.out.println(mensaje);
    }

    @Override
    public void enviarMensaje(String mensaje) {
        try {
            servidor.enviarMensaje(mensaje, this); // Aquí se pasa 'this' como cliente
        } catch (RemoteException e) {
            System.err.println("Error al enviar mensaje: " + e.toString());
            e.printStackTrace();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Ingrese la dirección IP del servidor: ");
            String ipServidor = scanner.nextLine().trim();

            System.out.print("Ingrese el puerto del servidor RMI (por defecto 1099): ");
            int puertoServidor = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Ingrese su nombre de usuario: ");
            String nombreUsuario = scanner.nextLine().trim();

            ChatServer servidor = (ChatServer) java.rmi.Naming.lookup("rmi://" + ipServidor + ":" + puertoServidor + "/ChatServer");

            ChatClient cliente = new ChatClientImpl(servidor, nombreUsuario);

            while (true) {
                String mensaje = scanner.nextLine();
                cliente.enviarMensaje(mensaje);
            }

        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.toString());
            e.printStackTrace();
        }
    }
}
