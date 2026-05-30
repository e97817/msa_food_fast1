package bo.gob.food_fast.atencion.service.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import bo.gob.food_fast.atencion.dto.dto.DtoCelular;
import bo.gob.food_fast.atencion.repository.repository.CelularDao;
import bo.gob.food_fast.atencion.entity.entities.CelularJPA;

/**
 * @author Wilson Limachi Choque
 * @since 16/05/2025
* Servicio CelularServicio.
*/
@RequestScoped
public class CelularServicio implements CelularServicioLocal {

	@Inject 
	CelularDao celularDao;
	
	private ModelMapper vModelMapper;
	
    @Inject
    public CelularServicio(CelularDao celularDao)
    {
        this.celularDao = celularDao;
        this.vModelMapper = new ModelMapper();
        this.vModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
    /**
	 * obtenerCelularPorId.
	 * 
	 * @return DatosCelular.
	 * 
	 */
    public RespuestaBaseResponse obtenerCelularPorId(Long pNumSec) 
    {
    	RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
    	DtoCelular vResultado = new DtoCelular();
    	
    	CelularJPA vRespuestaEntidad = this.celularDao.findById(pNumSec);
    	
    	if(vRespuestaEntidad != null)
    	{
    		vResultado = this.vModelMapper.map(vRespuestaEntidad, new TypeToken<DtoCelular>() {}.getType());
	    	
			vRespuesta.success = true;
			vRespuesta.mensajes = List.of("Se ha obtenido el dato");
			vRespuesta.respuesta = vResultado;
    	}
    	else
    	{
    		vRespuesta.success = false;
			vRespuesta.mensajes = List.of("No se ha obtenido el dato, Error Interno");
			vRespuesta.respuesta = null;
    	}
    	
		return vRespuesta;
	}
    
    /**
	 * obtenerCelularList.
	 * 
	 * @return Array de DtoCelular.
	 * 
	 */
    public RespuestaBaseResponse obtenerCelularList() 
    {
    	RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
    	List<DtoCelular> vResultado = new ArrayList<>();
    	
    	List<CelularJPA> vRespuestaEntidad = this.celularDao.findAll().list();
    	
    	if(vRespuestaEntidad != null)
    	{
    		vResultado = this.vModelMapper.map(vRespuestaEntidad, new TypeToken<List<DtoCelular>>() {}.getType());
	    	
			vRespuesta.success = true;
			vRespuesta.mensajes = List.of("Se ha obtenido los datos");
			vRespuesta.respuesta = vResultado;
    	}
    	else
    	{
    		vRespuesta.success = false;
			vRespuesta.mensajes = List.of("No se ha obtenido los datos, Error Interno");
			vRespuesta.respuesta = null;
    	}
    	
		return vRespuesta;
    }
    
    /**
	 * registrarCelular.
	 * 
	 * @return objeto DatosCelular.
	 * 
	 */
    public RespuestaBaseResponse registrarCelular(DtoCelular pDatosCelular) 
    {
    	RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
    	CelularJPA vResultado = new CelularJPA(); 
    	
    	try
    	{
	    	pDatosCelular.numSec = null;
	    	
	    	vResultado = this.vModelMapper.map(pDatosCelular, new TypeToken<CelularJPA>() {}.getType());	    	
	    	
	    	this.celularDao.persist(vResultado);
			this.vModelMapper.map(vResultado, pDatosCelular);
	    	
			vRespuesta.success = true;
			vRespuesta.mensajes = List.of("Se ha registrado los datos");
			vRespuesta.respuesta = pDatosCelular;
    	}
    	catch (Exception e) 
    	{
    		vRespuesta.success = false;
			vRespuesta.mensajes = List.of("No se ha registrado los datos, Error Interno");
			vRespuesta.respuesta = null;
		}
    	
    	return vRespuesta;
    }
    
    /**
	 * modificarCelular.
	 * 
	 * @return objeto DatosCelular.
	 * 
	 */
    public RespuestaBaseResponse modificarCelular(DtoCelular pDatosCelular) 
    {
    	RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
    	CelularJPA vResultado = new CelularJPA(); 
    	
    	try
    	{
	    	
			vResultado = this.celularDao.findById(pDatosCelular.numSec);
			if(vResultado != null)
			{
	    		this.vModelMapper.map(pDatosCelular, vResultado);	    	
	    		this.celularDao.getEm().merge(vResultado);
				this.vModelMapper.map(vResultado, pDatosCelular);
			
				vRespuesta.success = true;
				vRespuesta.mensajes = List.of("Se ha modificado el archivo");
				vRespuesta.respuesta = pDatosCelular;
			}
			else
			{
				vRespuesta.success = false;
				vRespuesta.mensajes = List.of("No se ha modificado el archivo, Dato no Encontrado");
				vRespuesta.respuesta = null;
			}    	}
    	catch (Exception e) 
    	{
    		vRespuesta.success = false;
			vRespuesta.mensajes = List.of("No se ha modificado el archivo, Error Interno");
			vRespuesta.respuesta = null;		}
    	
    	return vRespuesta;
    }
}
