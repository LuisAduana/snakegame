/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import controladores.*;
import interfaces.*;
import java.rmi.*;
import java.util.*;
import javafx.scene.input.*;
import org.junit.*;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.*;
import snake.*;

/**
 *
 * @author Alberto Sánchez
 */
public class ServidorSnakeTest {
  
  private static IServer server;
  private static ServidorSnake servidor;
  private static ServidorSnake servidorDos;
  private static ServidorSnake servidorTres;
  ArrayList<String> colores;
  List<Snake> serpientes;
  private static String colorSerpiente = "";
  private static String nombreUsuario = "";
  private static String nombreJugadorExpulsado = "";
  private static String nombreJugadorDisponibilidad = "";
  private static ClienteSnake clienteSnake;
  private static ClienteSnake clienteSnakeUno;
  private static ClienteSnake clienteSnakeDos;
  private static ClienteSnake clienteSnakeTres;
  private static ClienteSnake clienteSnakeCuatro;
  private static ClienteSnake clienteSnakeCinco;
  private static ClienteSnake clienteSnakeSeis;
  private static ClienteSnake clienteSnakeSiete;
  private static ClienteSnake clienteSnakeOcho;
  private static ClienteSnake clienteSnakeNueve;
  private static ClienteSnake clienteSnakeDiez;
  private static ClienteSnake clienteSnakeEliminarServidor;
  private static ClienteSnake clienteSnakeEliminarColor;
  private static ClienteSnake clienteSnakeMover;
  private static boolean resultadoDisponibilidad;
  private static KeyCode valorArriba;
  
  public ServidorSnakeTest() {   
  }
  
  @BeforeClass
  public static void setUpClass() throws RemoteException {
    clienteSnakeEliminarServidor = new ClienteSnake(server);
    clienteSnakeEliminarColor = new ClienteSnake(server);
    clienteSnakeUno = new ClienteSnake(server);
    clienteSnakeDos = new ClienteSnake(server);
    clienteSnakeTres = new ClienteSnake(server);
    clienteSnakeCuatro = new ClienteSnake(server);
    clienteSnakeCinco = new ClienteSnake(server);
    clienteSnakeSeis = new ClienteSnake(server);
    clienteSnakeSiete = new ClienteSnake(server);
    clienteSnakeOcho = new ClienteSnake(server);
    clienteSnakeNueve = new ClienteSnake(server);
    clienteSnakeDiez = new ClienteSnake(server);
    clienteSnake = new ClienteSnake(server);
    clienteSnakeMover = new ClienteSnake(server);
    servidor = new ServidorSnake();
    servidorDos = new ServidorSnake();
    servidorTres = new ServidorSnake();
    servidor.iniciarServidor();
    servidorDos.iniciarServidor();
    servidorTres.iniciarServidor();
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
   * Test of iniciarJugador method, of class ServidorSnake.
   */
  @org.junit.Test
  public void testIniciarJugador() throws Exception {
     setUpClass();
     this.nombreUsuario = "Prueba";
     this.servidor.iniciarJugador(clienteSnake, nombreUsuario);
     for (Snake serpiente : servidor.recuperarSerpientes()){
       Assert.assertEquals(serpiente.getNombre(), nombreUsuario);
       return;
    } 
     fail("No existe serpiente en el servidor.");
  }
  
  /**
   * Test of iniciarJugador method, of class ServidorSnake.
   */
  @org.junit.Test
  public void testJuegoLleno() throws Exception {
     setUpClass();
     this.servidor.iniciarJugador(clienteSnakeUno, "Prueba1");
     this.servidor.iniciarJugador(clienteSnakeDos, "Prueba2");
     this.servidor.iniciarJugador(clienteSnakeTres, "Prueba3");
     this.servidor.iniciarJugador(clienteSnakeCuatro, "Prueba4");
     this.servidor.iniciarJugador(clienteSnakeCinco, "Prueba5");
     this.servidor.iniciarJugador(clienteSnakeSeis, "Prueba6");
     this.servidor.iniciarJugador(clienteSnakeSiete, "Prueba7");
     this.servidor.iniciarJugador(clienteSnakeOcho, "Prueba8");
     this.servidor.iniciarJugador(clienteSnakeNueve, "Prueba9");
     this.servidor.iniciarJugador(clienteSnakeDiez, "Prueba10");
     resultadoDisponibilidad = servidor.esDisponible();
     Assert.assertFalse(resultadoDisponibilidad);
  }
  
  /**
   * Test of iniciarJugador method, of class ServidorSnake.
   */
  @org.junit.Test
  public void testExpulsarJugadorListaSerpientes() throws Exception {
     setUpClass();
     this.nombreJugadorExpulsado = "PruebaExpulsar";
     this.servidorDos.iniciarJugador(clienteSnakeEliminarServidor, nombreJugadorExpulsado);
     for (Snake serpiente : servidorDos.recuperarSerpientes()){
       if (serpiente.getNombre().equals(nombreJugadorExpulsado)){
         this.colorSerpiente = serpiente.getColorViva();
       }
     }
     this.servidorDos.eliminarSerpiente(this.colorSerpiente);
     for (Snake serpiente : servidorDos.recuperarSerpientes()){
       Assert.assertEquals(serpiente.getNombre(), nombreJugadorExpulsado);
       fail("Serpiente se encuentra en el servidor");
     } 
     return;
  }
  
  /**
   * Test of iniciarJugador method, of class ServidorSnake.
   */
  @org.junit.Test
  public void testDisponibilidadColorJugadorExpulsado() throws Exception {
     setUpClass();
     this.nombreJugadorDisponibilidad = "PruebaColor";
     this.servidorTres.iniciarJugador(clienteSnakeEliminarColor,nombreJugadorDisponibilidad);
     String colorSerpienteEliminada = this.servidorTres.getColores().get(0);
     this.servidorTres.eliminarSerpiente(clienteSnakeEliminarColor.getColor());
     for (String color : servidorTres.getColores()){
       Assert.assertEquals(colorSerpienteEliminada, color);
       return;
    } 
     fail("No existe color serpiente en el servidor.");
  }
  
  /**
   * Test of iniciarJugador method, of class ServidorSnake.
   */
  @org.junit.Test
  public void testMovimientoSerpienteArriba() throws Exception {
     setUpClass();
     Snake snake = null;
     this.nombreJugadorDisponibilidad = "PruebaColor";
     this.servidorTres.iniciarJugador(clienteSnakeMover,nombreJugadorDisponibilidad);
     for (Snake serpiente : this.servidorTres.getSerpientes()){
       if (serpiente.getNombre().equals(nombreJugadorDisponibilidad)){
         snake = serpiente;
       }
     }
     valorArriba = this.servidorTres.moverSerpiente(KeyCode.UP, snake.getColorViva());
     Assert.assertEquals(KeyCode.UP, valorArriba);
  }
  
  /**
   * Test of iniciarJugador method, of class ServidorSnake.
   */
  @org.junit.Test
  public void testMovimientoSerpienteDerecha() throws Exception {
     setUpClass();
     Snake snake = null;
     this.nombreJugadorDisponibilidad = "PruebaColor";
     this.servidorTres.iniciarJugador(clienteSnakeMover,nombreJugadorDisponibilidad);
     for (Snake serpiente : this.servidorTres.getSerpientes()){
       if (serpiente.getNombre().equals(nombreJugadorDisponibilidad)){
         snake = serpiente;
       }
     }
     valorArriba = this.servidorTres.moverSerpiente(KeyCode.RIGHT, snake.getColorViva());
     Assert.assertEquals(KeyCode.RIGHT, valorArriba);
  }
  
  /**
   * Test of iniciarJugador method, of class ServidorSnake.
   */
  @org.junit.Test
  public void testMovimientoSerpienteIzquierda() throws Exception {
     setUpClass();
     Snake snake = null;
     this.nombreJugadorDisponibilidad = "PruebaColor";
     this.servidorTres.iniciarJugador(clienteSnakeMover,nombreJugadorDisponibilidad);
     for (Snake serpiente : this.servidorTres.getSerpientes()){
       if (serpiente.getNombre().equals(nombreJugadorDisponibilidad)){
         snake = serpiente;
       }
     }
     valorArriba = this.servidorTres.moverSerpiente(KeyCode.LEFT, snake.getColorViva());
     Assert.assertEquals(KeyCode.LEFT, valorArriba);
  }
  
  /**
   * Test of iniciarJugador method, of class ServidorSnake.
   */
  @org.junit.Test
  public void testMovimientoSerpienteAbajo() throws Exception {
     setUpClass();
     Snake snake = null;
     this.nombreJugadorDisponibilidad = "PruebaColor";
     this.servidorTres.iniciarJugador(clienteSnakeMover,nombreJugadorDisponibilidad);
     for (Snake serpiente : this.servidorTres.getSerpientes()){
       if (serpiente.getNombre().equals(nombreJugadorDisponibilidad)){
         snake = serpiente;
       }
     }
     valorArriba = this.servidorTres.moverSerpiente(KeyCode.DOWN, snake.getColorViva());
     Assert.assertEquals(KeyCode.DOWN, valorArriba);
  }
  
//  /**
//   * Test of consultarPuntuaciones method, of class VistaPrincipallController.
//   */
//  @org.junit.Test
//  public void testValidarConexionBd() throws RemoteException {
//    setUpClass();
//    this.servidor.iniciarServidor();
//    servidor.getTablero();
////    servidor.
//    
//  }

}
