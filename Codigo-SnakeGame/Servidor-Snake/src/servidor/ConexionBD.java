package servidor;

import snake.PuntuacionObtenida;
import objetos.Puntuacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase que estructura las consultas hacia la base de datos.
 * @author Luis Bonilla
 * @author Rodrigo
 */
class ConexionBD {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    /**
     * Constructor de la clase ConexionBD.
     */
    ConexionBD() {
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
    
}
