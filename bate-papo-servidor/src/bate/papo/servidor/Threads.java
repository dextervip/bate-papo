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
public class Threads {
    
    public static ArrayList<Thread> threads = new ArrayList<Thread>();
    
    public static void addThread(Thread t){
        Threads.threads.add(t);
    }
    
    public static void removeThread(Thread t){
       Threads.threads.remove(t); 
    }
    
}
