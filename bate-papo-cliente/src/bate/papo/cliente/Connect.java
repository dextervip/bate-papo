package bate.papo.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para conexao com servidor
 */
public class Connect extends Thread {

    Socket client;
    private String ip;
    private int porta;
    private DataOutputStream dsaida;
    private DataInputStream entrada;

    public Connect(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
    }

    /**
     * Envia mensagem para servidor
     *
     * @param msg
     * @throws IOException
     */
    public void enviar(String msg) throws IOException {
        this.dsaida.writeUTF(msg);
        this.dsaida.flush();

    }
    /**
     * Realiza conexao com servidor e espera cliente enviar um mensagem para servidor
     */
    public void conectar() {
        try {
            this.client = new Socket(this.ip, this.porta);
            System.out.println("Conectado ao servidor.");
            dsaida = new DataOutputStream(this.client.getOutputStream());
            entrada = new DataInputStream(this.client.getInputStream());

            new ReadInput().start();
            while (true) {
                Thread.sleep(500);
                if (Mensagem.filaMsgSaida.size() > 0) {
                    System.out.println("Caiu na lista");
                    for (int i = 0; i < Mensagem.filaMsgSaida.size(); i++) {
                        String string = Mensagem.filaMsgSaida.get(i);
                        this.enviar(string);
                        Mensagem.filaMsgSaida.remove(i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    @Override
    public void run() {
        this.conectar();

    }
    /**
     * Classe que realiza a leitura de mensagem do servidor
     */
    private class ReadInput extends Thread {

        @Override
        public void run() {
            try {
                while (true) {
                    String msg = entrada.readUTF();
                    System.out.println(msg);
                    Mensagem.filaMsgEntrada.add(msg);
                }
            } catch (IOException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
