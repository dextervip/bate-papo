package bate.papo.servidor;

/**
 *
 * @author Rafael
 */
public class ProtocoloChat {

    public void process(Cliente c, String msg) throws Exception {
        System.out.println(c.getUsername() + ": " + msg);
        if (msg.startsWith("USER")) {
            String msgArray[] = msg.split(" ");
            c.setUsername(msgArray[1]);
            c.enviar("OK_USERNAME " + c.getUsername() + ":");
        } else if (msg.startsWith("MSG")) {
            String msgArray[] = msg.split(" ");
            for (int i = 0; i < Clientes.clientes.size(); i++) {
                Clientes.clientes.get(i).enviar("MSG_SENDED " + c.getUsername() + " " + msgArray[1]);
            }
            //c.enviar("MSG_SENDED " + c.getUsername() + " " + msgArray[1]);
        } else if (msg.startsWith("NAMES")) {
            String names = "";
            for (int i = 0; i < Clientes.clientes.size(); i++) {
                names += Clientes.clientes.get(i).getUsername() + " ";
            }
            c.enviar("NAMES " + names);
        } else {
            throw new Exception("Invalid Command");
        }

    }
}
