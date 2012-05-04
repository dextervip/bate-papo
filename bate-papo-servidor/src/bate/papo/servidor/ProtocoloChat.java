package bate.papo.servidor;

/**
 *
 * @author Rafael
 */
public class ProtocoloChat {

    public void process(Cliente c, String msg) throws Exception {

        if (msg.startsWith("USER")) {
            String msgArray[] = msg.split(" ", 1);
            c.setUsername(msgArray[1]);
            c.enviar("OK_USERNAME "+c.getUsername()+":");
        } else {
            throw new Exception("Invalid Command");
        }

    }
}
