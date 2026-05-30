package bo.gob.food_fast.atencion.repository.repository;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.persistence.Query;

import javax.enterprise.context.ApplicationScoped;
import bo.gob.food_fast.atencion.entity.constante.Util;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import bo.gob.food_fast.atencion.entity.entities.MesaJPA;

@ApplicationScoped
public class MesaDao implements PanacheRepository<MesaJPA> {

    @Inject
    EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public MesaJPA buscarPorId(Long id) {
        try {
            StringBuffer sbfConsulta = new StringBuffer("");
            HashMap<String, Object> mapParametros = new HashMap<String, Object>();

            sbfConsulta.append("select * FROM ");
            sbfConsulta.append(Util.ESQUEMA);
            sbfConsulta.append(".mesa a ");
            sbfConsulta.append("WHERE a.id = :id");
            mapParametros.put("id", id);
            Query query = em.createNativeQuery(sbfConsulta.toString(), MesaJPA.class);
            for (String key : mapParametros.keySet()) {
                query.setParameter(key, mapParametros.get(key));
            }
            return (MesaJPA) query.getSingleResult();
        } catch (Exception e) {
            return findById(id);
        }
    }

    public List<MesaJPA> listar() {
        try {
            StringBuffer sbfConsulta = new StringBuffer("");
            sbfConsulta.append("select * FROM ");
            sbfConsulta.append(Util.ESQUEMA);
            sbfConsulta.append(".mesa a ");
            Query query = em.createNativeQuery(sbfConsulta.toString(), MesaJPA.class);
            return query.getResultList();
        } catch (Exception e) {
            return findAll().list();
        }
    }

    public List<MesaJPA> listarPorEstado(String estado) {
        return list("estado", estado);
    }

    public void guardar(MesaJPA mesa) {
        persist(mesa);
    }

    public void actualizar(MesaJPA mesa) {
        em.merge(mesa);
    }

    public void eliminar(Long id) {
        deleteById(id);
    }
}