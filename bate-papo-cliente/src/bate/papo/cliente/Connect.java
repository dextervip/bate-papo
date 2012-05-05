package bate.papo.cliente;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
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
    
    public ArrayList<String> filaMsg = new ArrayList<String>();

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
            
            while (true) {
                System.out.println("loop");
                if(this.filaMsg.size() > 0){
                    System.out.println("Caiu na lista");
                    for (int i = 0; i < filaMsg.size(); i++) {
                        String string = filaMsg.get(i);
                        this.enviar(string);
                    }
                }
                
                System.out.println(entrada.readUTF());
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
}
