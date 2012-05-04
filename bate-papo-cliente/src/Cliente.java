
import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String args[]) {
        try {
// para se conectar ao servidor, cria-se objeto Socket.
// O primeiro parâmetro é o IP ou endereço da máquina que
// se quer conectar e o segundo é a porta da aplicação.
// Neste caso, usa-se o IP da máquina local (127.0.0.1)
// e a porta da aplicação ServidorDeEco (2000).
            Socket conexao = new Socket("10.2.9.229", 8080);
// uma vez estabelecida a comunicação, deve-se obter os
// objetos que permitem controlar o fluxo de comunicação
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            String linha;
// objetos que permitem a leitura do teclado
            BufferedReader teclado =
                    new BufferedReader(new InputStreamReader(System.in));
// loop principal
            while (true) {
// lê a linha do teclado
                System.out.print("> ");
                linha = teclado.readLine();
                
// envia para o servidor
                saida.println(linha);
// pega o que o servidor enviou
                linha = entrada.readLine();
                
                
// Verifica se é linha válida, pois se for null a conexão
// foi interrompida. Se ocorrer isso, termina a execução.
                if (linha == null) {
                    System.out.println("Conexão encerrada!");
                    break;
                }
// se a linha não for nula, deve-se imprimi-la no vídeo
                System.out.println(linha);
            }
        } catch (IOException e) {
// caso ocorra alguma excessão de E/S, mostre qual foi.
            System.out.println("IOException: " + e);
        }
    }
}