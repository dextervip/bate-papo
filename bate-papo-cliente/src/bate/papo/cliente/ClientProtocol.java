/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bate.papo.cliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class ClientProtocol extends Protocolo{
    
    private String ip,porta,conexao,nomeComand,nome,mensagem,comando;
    private String respEnvioNome;

    
    public void setComando(String comando){
        this.comando = comando;
    }
    
    public String getComando(){
    return comando;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }
    
     public String solicitaConexao(String texto){
     
          String[] b = texto.split(" ");
          this.setComando(b[0]);
          this.setIp(b[1]);
          this.setPorta(b[2]);
          
          if(getComando().equals(getSERVER())){
            try {
                Socket socket = new Socket(getIp(),Integer.parseInt(getPorta()));
            } catch (UnknownHostException ex) {
                Logger.getLogger(ClientProtocol.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ClientProtocol.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
          return conexao;
     
    }
    
     public String enviaNome(String nome){
         this.setNome(nome);
         
         nomeComand = this.getUSER()+" "+this.getNome();
         return nomeComand;
     }
    public String respEnvioNome(String resposta){
    
       
        if(resposta.equals(
            this.getOK_USERNAME()+" "+this.getNome())){
           
            respEnvioNome = this.getOK_USERNAME()+" "+this.getNome();
        }
        
        if(resposta.equals(
            this.getERR_INVALIDUSERNAME())){
           
            respEnvioNome = this.getERR_INVALIDUSERNAME()+" USER NAME INVALIDO";
        }
        
        if(resposta.equals(
            this.getERR_NEEDMOREPARAMS())){
           
            respEnvioNome = this.getERR_INVALIDUSERNAME()+" USER NAME AUSENTE";
        }
        
         if(resposta.equals(
            this.getERR_ALREADYREGISTRED())){
           
             respEnvioNome  = this.getERR_INVALIDUSERNAME()+" USER JÁ ESTA NA LISTA";
        }
        
        return  respEnvioNome ;
    }
    
    public String enviaMensagem(String msg){
        mensagem = this.getMSG()+" "+msg; 
        return mensagem;
    }
    
    
}
