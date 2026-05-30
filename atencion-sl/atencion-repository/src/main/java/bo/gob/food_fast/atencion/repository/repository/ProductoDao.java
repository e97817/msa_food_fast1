package bo.gob.food_fast.atencion.repository.repository;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.persistence.Query;

import javax.enterprise.context.ApplicationScoped;
import bo.gob.food_fast.atencion.entity.constante.Util;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import bo.gob.food_fast.atencion.entity.entities.ProductoJPA;

@ApplicationScoped
public class ProductoDao implements PanacheRepository<ProductoJPA> {

    @Inject
    EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public ProductoJPA buscarPorId(Long id) {
        try {
            StringBuffer sbfConsulta = new StringBuffer("");
            HashMap<String, Object> mapParametros = new HashMap<String, Object>();

            sbfConsulta.append("select * FROM ");
            sbfConsulta.append(Util.ESQUEMA);
            sbfConsulta.append(".producto a ");
            sbfConsulta.append("WHERE a.id = :id");
            mapParametros.put("id", id);
            Query query = em.createNativeQuery(sbfConsulta.toString(), ProductoJPA.class);
            for (String key : mapParametros.keySet()) {
                query.setParameter(key, mapParametros.get(key));
            }
            return (ProductoJPA) query.getSingleResult();
        } catch (Exception e) {
            return findById(id);
        }
    }

    public List<ProductoJPA> listar() {
        try {
            StringBuffer sbfConsulta = new StringBuffer("");
            sbfConsulta.append("select * FROM ");
            sbfConsulta.append(Util.ESQUEMA);
            sbfConsulta.append(".producto a ");
            Query query = em.createNativeQuery(sbfConsulta.toString(), ProductoJPA.class);
            return query.getResultList();
        } catch (Exception e) {
            return findAll().list();
        }
    }

    public List<ProductoJPA> listarDisponibles() {
        return list("disponible", true);
    }

    public void guardar(ProductoJPA producto) {
        persist(producto);
    }

    public void actualizar(ProductoJPA producto) {
        em.merge(producto);
    }

    public void eliminar(Long id) {
        deleteById(id);
    }
}