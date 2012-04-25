package bate.papo.servidor;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Rafael
 */
public class BatePapoServidor {

    ServerSocket server;
    //final int port = 5588;
    final int port = 8080;

    public BatePapoServidor() {
        System.out.println("Iniciando Servidor");
        try {
            this.server = new ServerSocket(this.port);
            System.out.println("Servidor iniciado na porta: "+this.port);
            this.escutar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public Socket escutar() {
        Socket cliente = null;
        try{
            System.out.println("Servidor aguardando clientes...");
            cliente = this.server.accept();
            System.out.println("Cliente conectado");
            System.out.println("IP: "+cliente.getInetAddress().getHostAddress());
            System.out.println("Hostname: "+cliente.getInetAddress().getHostName());

            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            while(true)
            System.out.println(entrada.readUTF());

        }catch(Exception e){
            e.printStackTrace();
        }
        return cliente;
    }

    public static void main(String[] args) {
        new BatePapoServidor();
    }
}
