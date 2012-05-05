package bate.papo.cliente;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.net.ConnectionResetException;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Rafael
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

    public void enviar(String msg) throws IOException {
        this.dsaida.writeUTF(msg);
        this.dsaida.flush();

    }

    public void conectar() {

        try {

            this.client = new Socket(this.ip, this.porta);
            System.out.println("Conectado ao servidor.");
            dsaida = new DataOutputStream(this.client.getOutputStream());
            entrada = new DataInputStream(this.client.getInputStream());

//            dsaida.writeUTF("USER pedro");
//            dsaida.flush();
//            dsaida.writeUTF("MSG tommaaaaaa");
//            dsaida.flush();
//            dsaida.writeUTF("MSG noob");
//            dsaida.flush();
//            dsaida.writeUTF("LOL");
//            dsaida.flush();
//            dsaida.writeUTF("NAMES");
//            dsaida.flush();
            new ReadInput().start();
            while (true) {
                //System.out.println("loop");
                Thread.sleep(500);
                if (Mensagem.filaMsgSaida.size() > 0) {
                    System.out.println("Caiu na lista");
                    for (int i = 0; i < Mensagem.filaMsgSaida.size(); i++) {
                        String string = Mensagem.filaMsgSaida.get(i);
                        this.enviar(string);
                        Mensagem.filaMsgSaida.remove(i);
                    }
                }
//                if (entrada) {
//
//                    System.out.println(entrada.readUTF());
//                }
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
