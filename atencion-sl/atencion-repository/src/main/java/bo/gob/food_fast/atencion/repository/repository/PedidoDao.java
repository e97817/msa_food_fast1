package bo.gob.food_fast.atencion.repository.repository;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.persistence.Query;

import javax.enterprise.context.ApplicationScoped;
import bo.gob.food_fast.atencion.entity.constante.Util;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import bo.gob.food_fast.atencion.entity.entities.PedidoJPA;

@ApplicationScoped
public class PedidoDao implements PanacheRepository<PedidoJPA> {

    @Inject
    EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public PedidoJPA buscarPorId(Long id) {
        try {
            StringBuffer sbfConsulta = new StringBuffer("");
            HashMap<String, Object> mapParametros = new HashMap<String, Object>();

            sbfConsulta.append("select * FROM ");
            sbfConsulta.append(Util.ESQUEMA);
            sbfConsulta.append(".pedido a ");
            sbfConsulta.append("WHERE a.id = :id");
            mapParametros.put("id", id);
            Query query = em.createNativeQuery(sbfConsulta.toString(), PedidoJPA.class);
            for (String key : mapParametros.keySet()) {
                query.setParameter(key, mapParametros.get(key));
            }
            return (PedidoJPA) query.getSingleResult();
        } catch (Exception e) {
            return findById(id);
        }
    }

    public List<PedidoJPA> listar() {
        try {
            StringBuffer sbfConsulta = new StringBuffer("");
            sbfConsulta.append("select * FROM ");
            sbfConsulta.append(Util.ESQUEMA);
            sbfConsulta.append(".pedido a ");
            Query query = em.createNativeQuery(sbfConsulta.toString(), PedidoJPA.class);
            return query.getResultList();
        } catch (Exception e) {
            return findAll().list();
        }
    }

    public List<PedidoJPA> listarPorCliente(Long clienteId) {
        return list("clienteId", clienteId);
    }

    public void guardar(PedidoJPA pedido) {
        persist(pedido);
    }

    public void actualizar(PedidoJPA pedido) {
        em.merge(pedido);
    }

    public void eliminar(Long id) {
        deleteById(id);
    }
}