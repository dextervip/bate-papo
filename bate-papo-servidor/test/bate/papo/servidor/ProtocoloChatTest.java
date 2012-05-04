/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bate.papo.servidor;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Rafael
 */
public class ProtocoloChatTest {
    
    public ProtocoloChatTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of process method, of class ProtocoloChat.
     */
    @Test
    public void testProcess() {
        System.out.println("process");
        String msg = "USER joao";
        ProtocoloChat instance = new ProtocoloChat();
        try {
            instance.process(null, msg);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
        
    }
    
    
}
