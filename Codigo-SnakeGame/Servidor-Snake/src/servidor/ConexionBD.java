package servidor;

import snake.PuntuacionObtenida;
import objetos.Puntuacion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import snake.Snake;

/**
 * Clase que estructura las consultas hacia la base de datos.
 * @author Luis Bonilla
 * @author Rodrigo
 */
public class ConexionBD {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    /**
     * Constructor de la clase ConexionBD.
     */
    public ConexionBD() {
        emf = Persistence.createEntityManagerFactory("Servidor-SnakePU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    /**
     * Realiza una petición a la BD.
     * @return List<PuntuacionObtenida> Regresa una lista de puntuaciones obtenidas.
     */
    public List<PuntuacionObtenida> consultarPuntiaciones () {
        List<Puntuacion> puntuacionesConsultadas;   
        puntuacionesConsultadas = em.createNamedQuery("Puntuacion.findSortByPuntuacion", Puntuacion.class).getResultList();
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        
        List<PuntuacionObtenida> puntuacionesEnviadas = new ArrayList<>();
        
        for (Puntuacion puntuacion : puntuacionesConsultadas) {
            PuntuacionObtenida puntuacionObtenida = new PuntuacionObtenida();
            puntuacionObtenida.setIdcliente(puntuacion.getIdcliente());
            puntuacionObtenida.setNombre(puntuacion.getNombre());
            puntuacionObtenida.setPuntuacion(puntuacion.getPuntuacion());
            puntuacionesEnviadas.add(puntuacionObtenida);
        }
        
        return puntuacionesEnviadas;
    }

    public List<Snake> registrarPuntuaciones(List<Snake> listaPuntuaciones) {
        try {
            for (Snake snake : listaPuntuaciones) {
                Puntuacion puntuacion = new Puntuacion();
                Integer idPuntuacion = contarRegistros() + 1;
                puntuacion.setIdcliente(idPuntuacion);
                puntuacion.setNombre(snake.getNombre());
                puntuacion.setPuntuacion(snake.getPuntuacion());
                em.persist(puntuacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (!listaPuntuaciones.isEmpty()) {
                listaPuntuaciones.clear();
            }
            em.close();
            emf.close();
        }
        return listaPuntuaciones;
    }
    
    private Integer contarRegistros() {
        Query query = em.createQuery("SELECT COUNT(p) FROM Puntuacion p");
        long resultado = (Long) query.getSingleResult();
        return (int) resultado;
    }
    
}
