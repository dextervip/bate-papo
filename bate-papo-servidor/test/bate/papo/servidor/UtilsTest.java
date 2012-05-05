/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bate.papo.servidor;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Rafael
 */
public class UtilsTest {
    
    public UtilsTest() {
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
     * Test of arrayToString2 method, of class Utils.
     */
    @Test
    public void testArrayToString2() {
        System.out.println("arrayToString2");
        String[] a = null;
        String separator = "";
        String expResult = "";
        String result = Utils.arrayToString(a, separator);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarUsername method, of class Utils.
     */
    @Test
    public void testValidarUsername() {
        System.out.println("validarUsername");
        String username = "joaojoao";
        boolean result = Utils.validarUsername(username);
        assertEquals(true, result);
    }
}
