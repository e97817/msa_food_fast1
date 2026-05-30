package bo.gob.food_fast.atencion.repository.repository;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.persistence.Query;

import javax.enterprise.context.ApplicationScoped;
import bo.gob.food_fast.atencion.entity.constante.Util;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import bo.gob.food_fast.atencion.entity.entities.ClienteJPA;

@ApplicationScoped
public class ClienteDao implements PanacheRepository<ClienteJPA> {

    @Inject
    EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public ClienteJPA buscarPorId(Long id) {
        try {
            StringBuffer sbfConsulta = new StringBuffer("");
            HashMap<String, Object> mapParametros = new HashMap<String, Object>();

            sbfConsulta.append("select * FROM ");
            sbfConsulta.append(Util.ESQUEMA);
            sbfConsulta.append(".cliente a ");
            sbfConsulta.append("WHERE a.id = :id");
            mapParametros.put("id", id);
            Query query = em.createNativeQuery(sbfConsulta.toString(), ClienteJPA.class);
            for (String key : mapParametros.keySet()) {
                query.setParameter(key, mapParametros.get(key));
            }
            return (ClienteJPA) query.getSingleResult();
        } catch (Exception e) {
            return findById(id);
        }
    }

    public List<ClienteJPA> listar() {
        try {
            StringBuffer sbfConsulta = new StringBuffer("");
            sbfConsulta.append("select * FROM ");
            sbfConsulta.append(Util.ESQUEMA);
            sbfConsulta.append(".cliente a ");
            Query query = em.createNativeQuery(sbfConsulta.toString(), ClienteJPA.class);
            return query.getResultList();
        } catch (Exception e) {
            return findAll().list();
        }
    }

    public void guardar(ClienteJPA cliente) {
        persist(cliente);
    }

    public void actualizar(ClienteJPA cliente) {
        em.merge(cliente);
    }

    public void eliminar(Long id) {
        deleteById(id);
    }
}