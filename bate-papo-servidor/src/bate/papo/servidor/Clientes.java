package bate.papo.servidor;
import java.util.ArrayList;

/**
 * Claase que armazena a lista de clientes ativos
 */
public class Clientes {
    
    public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    
    /**
     * Adiciona cliente na lista
     * @param c 
     */
    public static void addCliente(Cliente c){
        Clientes.clientes.add(c);
    }
    /**
     * Remove um cliente da lista
     * @param c 
     */
    public static void removeCliente(Cliente c){
       Clientes.clientes.remove(c); 
    }
    
}
