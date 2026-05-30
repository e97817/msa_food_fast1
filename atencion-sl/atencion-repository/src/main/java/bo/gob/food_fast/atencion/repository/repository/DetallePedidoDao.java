package bo.gob.food_fast.atencion.repository.repository;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.persistence.Query;

import javax.enterprise.context.ApplicationScoped;
import bo.gob.food_fast.atencion.entity.constante.Util;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import bo.gob.food_fast.atencion.entity.entities.DetallePedidoJPA;

@ApplicationScoped
public class DetallePedidoDao implements PanacheRepository<DetallePedidoJPA> {

    @Inject
    EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public DetallePedidoJPA buscarPorId(Long id) {
        try {
            StringBuffer sbfConsulta = new StringBuffer("");
            HashMap<String, Object> mapParametros = new HashMap<String, Object>();

            sbfConsulta.append("select * FROM ");
            sbfConsulta.append(Util.ESQUEMA);
            sbfConsulta.append(".detalle_pedido a ");
            sbfConsulta.append("WHERE a.id = :id");
            mapParametros.put("id", id);
            Query query = em.createNativeQuery(sbfConsulta.toString(), DetallePedidoJPA.class);
            for (String key : mapParametros.keySet()) {
                query.setParameter(key, mapParametros.get(key));
            }
            return (DetallePedidoJPA) query.getSingleResult();
        } catch (Exception e) {
            return findById(id);
        }
    }

    public List<DetallePedidoJPA> listar() {
        try {
            StringBuffer sbfConsulta = new StringBuffer("");
            sbfConsulta.append("select * FROM ");
            sbfConsulta.append(Util.ESQUEMA);
            sbfConsulta.append(".detalle_pedido a ");
            Query query = em.createNativeQuery(sbfConsulta.toString(), DetallePedidoJPA.class);
            return query.getResultList();
        } catch (Exception e) {
            return findAll().list();
        }
    }

    public List<DetallePedidoJPA> listarPorPedido(Long pedidoId) {
        return list("pedidoId", pedidoId);
    }

    public void guardar(DetallePedidoJPA detalle) {
        persist(detalle);
    }

    public void actualizar(DetallePedidoJPA detalle) {
        em.merge(detalle);
    }

    public void eliminar(Long id) {
        deleteById(id);
    }
}