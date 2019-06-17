/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alberto Sánchez
 */
public class TableroTest {
  
  public TableroTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of wrap method, of class Tablero.
   */
  @Test
  public void testWrap() {
    System.out.println("wrap");
    Coordenada coord = null;
    Tablero instance = null;
    Coordenada expResult = null;
    Coordenada result = instance.wrap(coord);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of actualizarPosicion method, of class Tablero.
   */
  @Test
  public void testActualizarPosicion() {
    System.out.println("actualizarPosicion");
    Tablero instance = null;
    instance.actualizarPosicion();
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getColumnas method, of class Tablero.
   */
  @Test
  public void testGetColumnas() {
    System.out.println("getColumnas");
    Tablero instance = null;
    int expResult = 0;
    int result = instance.getColumnas();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getFilas method, of class Tablero.
   */
  @Test
  public void testGetFilas() {
    System.out.println("getFilas");
    Tablero instance = null;
    int expResult = 0;
    int result = instance.getFilas();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAncho method, of class Tablero.
   */
  @Test
  public void testGetAncho() {
    System.out.println("getAncho");
    Tablero instance = null;
    int expResult = 0;
    int result = instance.getAncho();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getAltura method, of class Tablero.
   */
  @Test
  public void testGetAltura() {
    System.out.println("getAltura");
    Tablero instance = null;
    int expResult = 0;
    int result = instance.getAltura();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getSnakes method, of class Tablero.
   */
  @Test
  public void testGetSnakes() {
    System.out.println("getSnakes");
    Tablero instance = null;
    List<Snake> expResult = null;
    List<Snake> result = instance.getSnakes();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setSnakes method, of class Tablero.
   */
  @Test
  public void testSetSnakes() {
    System.out.println("setSnakes");
    List<Snake> serpientes = null;
    Tablero instance = null;
    instance.setSnakes(serpientes);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getComida method, of class Tablero.
   */
  @Test
  public void testGetComida() {
    System.out.println("getComida");
    Tablero instance = null;
    Comida expResult = null;
    Comida result = instance.getComida();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setComida method, of class Tablero.
   */
  @Test
  public void testSetComida() {
    System.out.println("setComida");
    Comida comida = null;
    Tablero instance = null;
    instance.setComida(comida);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
