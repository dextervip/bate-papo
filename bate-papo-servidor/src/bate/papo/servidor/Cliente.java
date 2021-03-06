package bate.papo.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que representa um conexão do cliente com o servidor
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
    /**
     * Realiza a leitura de mensagens e passa para a classe ProcotoChat interpretar
     * @return void
     */
    public void process() {
        try {
            ProtocoloChat pc = new ProtocoloChat();
            pc.process(this, this.ler());
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().equalsIgnoreCase("Connection reset")) {
                Clientes.removeCliente(this);
                this.stop();
                this.destroy();
                System.out.println("Um cliente foi desconectado");
            }
        }
    }
    /**
     * Espera por uma mensagem do cliente
     * @return mensagem enviada pelo cliente
     * @throws IOException 
     */
    public String ler() throws IOException {
        DataInputStream entrada = new DataInputStream(cliente.getInputStream());
        String input = entrada.readUTF();
        return input;
    }
    /**
     * Envia uma mensagem para cliente
     * @param msg
     * @throws IOException 
     */
    public void enviar(String msg) throws IOException {
        DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
        saida.writeUTF(msg);
        saida.flush();
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
    /**
     * Método para pegar um username
     * @return  username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Método para alterar um username
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    /**
     * Recupera IP, Hostname, gera um nome aleatorio e manda uma mensagem de boas vindas ao cliente.
     */
    public void run() {
        this.setIp(this.cliente.getInetAddress().getHostAddress());
        this.setHostname(this.cliente.getInetAddress().getHostName());
        this.setUsername("guest" + System.identityHashCode(this));
        try {
            this.enviar("Conectado. Bem Vindo ao Chat. Digite USER e seu nome para alterar seu username.");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            this.process();
        }
    }
}
