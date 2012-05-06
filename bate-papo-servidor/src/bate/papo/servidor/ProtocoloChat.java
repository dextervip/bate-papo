package bate.papo.servidor;

/**
 * Classe que trata o protocolo
 *
 * @author Rafael
 */
public class ProtocoloChat {

    /**
     * Processa a mensagem recebida do cliente de acordo com o protocolo
     *
     * @param emissorMsg Cliente que enviou a mensagem
     * @param msg Mensagem enviada pelo cliente
     * @throws Exception
     */
    public void process(Cliente emissorMsg, String msg) throws Exception {
        System.out.println(emissorMsg.getUsername() + ": " + msg);
        if (msg.startsWith("USER")) {
            String msgArray[] = msg.split(" ");
            if (msgArray.length > 1) {
                if (Utils.validarUsername(msgArray[1]) == false) {
                    emissorMsg.enviar("ERR_INVALIDUSERNAME");
                } else {
                    //verifica se não existe outro cliente com mesmo nome
                    for (int i = 0; i < Clientes.clientes.size(); i++) {
                        if(Clientes.clientes.get(i).getUsername().equalsIgnoreCase(msgArray[1])){
                            emissorMsg.enviar("ERR_ALREADYREGISTRED "+msgArray[1]);
                            return;
                        }
                    }
                    emissorMsg.setUsername(msgArray[1]);
                    emissorMsg.enviar("OK_USERNAME " + emissorMsg.getUsername());
                }
            } else {
                emissorMsg.enviar("ERR_NEEDMOREPARAMS");                
            }
            
        } else if (msg.startsWith("MSG")) {
            String msgArray[] = msg.split(" ");
            msgArray[0] = "";
            String newMsg = Utils.arrayToString(msgArray, " ");
            for (int i = 0; i < Clientes.clientes.size(); i++) {
                Clientes.clientes.get(i).enviar("MSG_SENDED " + emissorMsg.getUsername() + " " + newMsg);
            }
            //c.enviar("MSG_SENDED " + c.getUsername() + " " + msgArray[1]);
        } else if (msg.startsWith("NAMES")) {
            String names = "";
            for (int i = 0; i < Clientes.clientes.size(); i++) {
                names += Clientes.clientes.get(i).getUsername() + " ";
            }
            emissorMsg.enviar("NAMES " + names);
        } else {
            emissorMsg.enviar("Invalid Command");
        }
        
    }
}
