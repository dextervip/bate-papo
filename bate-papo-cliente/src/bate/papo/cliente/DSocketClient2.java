package bate.papo.cliente;


import java.io.*;
import java.net.*;
import sun.net.ConnectionResetException;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Rafael
 */
public class DSocketClient2 {

    Socket client;
    final String host = "localhost";
    final int port = 8080;

    public DSocketClient2() {

        this.init();


    }

    public void init() {

        InetSocketAddress ip = new InetSocketAddress(this.host, this.port);
        System.out.println("Procurando servidor...");
        if (ip.isUnresolved()) {

            System.out.println("Não foi possível encontrar...tentando novamente...");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.init();

        } else {

            System.out.println("Servidor encontrado");
            this.conectar();
        }

    }

    public void conectar() {

        try {

            this.client = new Socket(this.host, this.port);
            System.out.println("Conectado ao servidor.");
            DataOutputStream dsaida = new DataOutputStream(this.client.getOutputStream());
            DataInputStream entrada = new DataInputStream(this.client.getInputStream());

            dsaida.writeUTF("USER joao");
            dsaida.flush();
            dsaida.writeUTF("MSG tommaaaaaa");
            dsaida.flush();
            while (true) {
                System.out.println(entrada.readUTF());
            }

        } catch (java.net.ConnectException e) {
            System.out.println("Conexão recusada...\nTentando novamente em 5 segundos...");
            try {
                Thread.sleep(5000);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            this.conectar();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new DSocketClient2();
    }
}
