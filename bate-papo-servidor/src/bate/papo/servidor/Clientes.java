/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bate.papo.servidor;

import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class Clientes {
    
    public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    
    public static void addCliente(Cliente t){
        Clientes.clientes.add(t);
    }
    
    public static void removeCliente(Cliente t){
       Clientes.clientes.remove(t); 
    }
    
}
