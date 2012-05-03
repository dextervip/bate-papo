package bate.papo.servidor;

import java.io.DataInputStream;
import java.net.Socket;

/**
 *
 * @author Rafael
 */
public class ProtocoloChat implements Runnable {

    Socket cliente;

    public ProtocoloChat(Socket cliente) {
        this.cliente = cliente;
    }

    public void process() {
        try {
            System.out.println("Cliente conectado");
            System.out.println("IP: " + cliente.getInetAddress().getHostAddress());
            System.out.println("Hostname: " + cliente.getInetAddress().getHostName());

            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            while (true) {
                System.out.println(System.identityHashCode(this) + " " + entrada.readUTF());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.process();
    }
}
