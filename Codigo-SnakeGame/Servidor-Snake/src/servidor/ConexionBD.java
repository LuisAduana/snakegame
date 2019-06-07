package servidor;

import clases.PuntuacionObtenida;
import objetos.Puntuacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

class ConexionBD {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    private List<Puntuacion> puntuacionesConsultadas;
    private List<PuntuacionObtenida> puntuacionesEnviadas;
    
    ConexionBD() {
        emf = Persistence.createEntityManagerFactory("Servidor-SnakePU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public List<PuntuacionObtenida> consultarPuntiaciones () {
        puntuacionesConsultadas = new ArrayList<>();    
        puntuacionesConsultadas = em.createNamedQuery("Puntuacion.findSortByPuntuacion", Puntuacion.class).getResultList();
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
        
        puntuacionesEnviadas = new ArrayList<>();
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
