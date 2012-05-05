/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bate.papo.cliente;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class Protocolo {

    private String comando;
    private String resposta;
    private String SERVER = "SERVER";
    private String USER = "USER";
    private String MSG = "MSG";
    private String OK_USERNAME = "OK_USERNAME";
    private String ERR_INVALIDUSERNAME = "ERR_INVALIDUSERNAME";
    private String ERR_NEEDMOREPARAMS = "ERR_NEEDMOREPARAMS";
    private String ERR_ALREADYREGISTRED = "ERR_ALREADYREGISTRED";
    private String MSG_SENDED = "MSG_SENDED";
    private String PRIVMSG = "PRIVMSG";
    private String ERR_NOSUCHNICK = "ERR_NOSUCHNICK";
    private String NOTEXTTOSEND = "NOTEXTTOSEND";
    private String PRIVMSG_SENDED = "PRIVMSG_SENDED";
    private String NAMES = "NAMES";
    private String QUIT = "QUIT";
    Connect connect;

    public String getMSG() {
        return MSG;
    }

    public String getSERVER() {
        return SERVER;
    }

    public String getUSER() {
        return USER;
    }

    public String getERR_ALREADYREGISTRED() {
        return ERR_ALREADYREGISTRED;
    }

    public String getERR_INVALIDUSERNAME() {
        return ERR_INVALIDUSERNAME;
    }

    public String getERR_NEEDMOREPARAMS() {
        return ERR_NEEDMOREPARAMS;
    }

    public String getERR_NOSUCHNICK() {
        return ERR_NOSUCHNICK;
    }

    public String getMSG_SENDED() {
        return MSG_SENDED;
    }

    public String getNAMES() {
        return NAMES;
    }

    public String getNOTEXTTOSEND() {
        return NOTEXTTOSEND;
    }

    public String getOK_USERNAME() {
        return OK_USERNAME;
    }

    public String getPRIVMSG() {
        return PRIVMSG;
    }

    public String getPRIVMSG_SENDED() {
        return PRIVMSG_SENDED;
    }

    public String getQUIT() {
        return QUIT;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public void process(String texto) {

        if (texto.startsWith("SERVER")) {

            String vetor[] = texto.split(" ");
            String ip = vetor[1];
            String porta = vetor[2];

            connect = new Connect(ip, Integer.parseInt(porta));
            connect.start();

        } else {
            connect.filaMsg.add(texto);
        }
    }
}
