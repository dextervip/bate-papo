package bate.papo.servidor;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe para servidor de batepapo
 */
public class BatePapoServidor {

    ServerSocket server;
    //final int port = 5588;
    final int port = 8080;

    public BatePapoServidor() {
        System.out.println("Iniciando Servidor");
        try {
            this.server = new ServerSocket(this.port);
            System.out.println("Servidor iniciado na porta: " + this.port);
            this.escutar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Faz o servidor escutar os clientes e coloca o cliente em uma thread
     * @return Socket cliente
     */
    public Socket escutar() {
        Socket cliente = null;
        try {
            while (true) {
                Cliente c = new Cliente(this.server.accept());
                c.start();
                Clientes.addCliente(c);
                System.out.println("Números de Clientes:" + Clientes.clientes.size());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public static void main(String[] args) {
        new BatePapoServidor();
    }
}
