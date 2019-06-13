/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.*;
import java.rmi.*;
import java.util.*;
import controladores.*;
import java.util.logging.*;
import javafx.stage.*;
import junit.framework.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import snake.*;

/**
 *
 * @author Alberto Sánchez
 */
public class VistaPrincipalControllerTest {
  
  private static VistaPrincipalController vistaPrincipal;
  private static String respuestaValidacion;
  private static String datoPrueba = "";
  private static final String RESPUESTA_ESPERADA_MAYOR = "Nombre debe ser menor a 12 caracteres";
  private static final String RESPUESTA_ESPERADA_MENOR = "Nombre es demasiado corto";
  private static final String RESPUESTA_ESPERADA_VACIO = "Ingrese un nombre";
  private static final String RESPUESTA_ESPERADA_INCORRECTO = "El nombre solo puede contener minúsculas, mayúsculas y/o números";
  private static final String RESPUESTA_ESPERADA_CORRECTO = "";
  
  public VistaPrincipalControllerTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    vistaPrincipal = new VistaPrincipalController();
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
  
  
  @Test(expected = RemoteException.class)
  public void testRemoteExceptionPreparaConexion() throws RemoteException {
  this.vistaPrincipal.prepararConexion();
}
  
  /**
   * Test of ValidarNickname method, of class VistaPrincipallController.
   */
  @org.junit.Test
  public void testValidarNicknameValorCorrecto(){
  setUpClass();
  this.datoPrueba = "Beto69";
  respuestaValidacion = vistaPrincipal.validarNickname(datoPrueba);
  assertEquals(RESPUESTA_ESPERADA_CORRECTO, respuestaValidacion);
  }
  
   /**
   * Test of ValidarNickname method, of class VistaPrincipallController.
   */
  @org.junit.Test
  public void testValidarNicknameValorVacio(){
  setUpClass();
  this.datoPrueba = "";
  respuestaValidacion = vistaPrincipal.validarNickname(datoPrueba);
  assertEquals(RESPUESTA_ESPERADA_VACIO, respuestaValidacion);
  }
  
  /**
   * Test of ValidarNickname method, of class VistaPrincipallController.
   */
  @org.junit.Test
  public void testValidarNicknameValorMayorMaximo(){
  setUpClass();
  this.datoPrueba = "ENTRADAEXTREMADAMENTELARGA";
  respuestaValidacion = vistaPrincipal.validarNickname(datoPrueba);
  assertEquals(RESPUESTA_ESPERADA_MAYOR, respuestaValidacion);
  }
  
   /**
   * Test of ValidarNickname method, of class VistaPrincipallController.
   */
  @org.junit.Test
  public void testValidarNicknameValorMayorMinimo(){
  setUpClass();
  this.datoPrueba = "ay";
  respuestaValidacion = vistaPrincipal.validarNickname(datoPrueba);
  assertEquals(RESPUESTA_ESPERADA_MENOR, respuestaValidacion);
  }
  
  /**
   * Test of ValidarNickname method, of class VistaPrincipallController.
   */
  @org.junit.Test
  public void testValidarNicknameValorDatosInvalidos(){
  setUpClass();
  this.datoPrueba = "()#$#%#%#&";
  respuestaValidacion = vistaPrincipal.validarNickname(datoPrueba);
  assertEquals(RESPUESTA_ESPERADA_INCORRECTO, respuestaValidacion);
  }

}
