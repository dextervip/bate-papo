package bate.papo.servidor;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class Cliente extends Thread {

    Socket cliente;
    
    private String username;
    private String ip;
    private String hostname;
    
    private ArrayList<String> entrada;
    private ArrayList<String> saida;

    public Cliente(Socket cliente) {
        this.cliente = cliente;
    }

    public void process() {
        try {
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            System.out.println(this.getUsername() + " " + entrada.readUTF());
        } catch (Exception e) {
            e.printStackTrace();
            if(e.getMessage().equalsIgnoreCase("Connection reset")){
                Clientes.removeCliente(this);
                this.stop();
                this.destroy();
            }
        }
    }
    
    public void enviar(){
        
    }
    public void ler(){
        
    }
    
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        this.setIp(this.cliente.getInetAddress().getHostAddress());
        this.setHostname(this.cliente.getInetAddress().getHostName());
        this.setUsername("guest"+System.identityHashCode(this));
        while (true) {
            this.process();
        }
    }
}
