package bo.gob.food_fast.atencion.repository.repository;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.inject.Inject;
import javax.persistence.Query;

import javax.enterprise.context.ApplicationScoped;
import bo.gob.food_fast.atencion.entity.constante.Util;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.persistence.EntityManager;
import bo.gob.food_fast.atencion.entity.entities.CelularJPA;

/**
 * @author Wilson Limachi Choque
 * @since 06/03/2024
 * @Actividad --
 */
@ApplicationScoped
public class CelularDao implements PanacheRepository<CelularJPA> {

    @Inject
    EntityManager em;
    	
	public EntityManager getEm() {
		return em;
	}
    	
    public CelularJPA obtenerCelularDaoId(Long pNumSec) 
    {
    	try
    	{
	        StringBuffer sbfConsulta = new StringBuffer("");
	        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
	
	        sbfConsulta.append("select * FROM ");
	        sbfConsulta.append(Util.ESQUEMA);
	        sbfConsulta.append(".celular a ");
	        sbfConsulta.append("WHERE a.num_sec = :numSec");
	        mapParametros.put("numSec", pNumSec);
			Query query = em.createNativeQuery(sbfConsulta.toString(), CelularJPA.class);	
	        return (CelularJPA) query.getSingleResult();
    	}
    	catch(Exception e)
    	{
    		return null;
    	}
    }

    public List<CelularJPA> obtenerCelularDao() 
    {
    	try
    	{
	        StringBuffer sbfConsulta = new StringBuffer("");
	        HashMap<String, Object> mapParametros = new HashMap<String, Object>();
	
	        sbfConsulta.append("select * FROM ");
	        sbfConsulta.append(Util.ESQUEMA);
	        sbfConsulta.append(".celular a ");
	        
			mapParametros = null;
         Query query = em.createNativeQuery(sbfConsulta.toString(), CelularJPA.class);	
	        return query.getResultList();
    	}
    	catch(Exception e)
    	{
    		return null;
    	}
    }
    
}
