package org.gdgrome.lab.gdgcultfest.endpoints;


import java.util.logging.Logger;

import javax.inject.Named;

import org.gdgrome.lab.gdgcultfest.beans.CultPojo;
import org.gdgrome.lab.gdgcultfest.common.MessageKeyDispatcher;
import org.gdgrome.lab.gdgcultfest.exceptions.SPARQLServiceException;
import org.gdgrome.lab.gdgcultfest.services.SPARQLQueryServices;
import org.gdgrome.lab.gdgcultfest.services.impl.SPARQLQueryServicesImpl;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;


@Api(
		name="apiCultBackend", 
		version="v1", 
		description="Api Codelab Devfest 2014", 
		title="Api Codelab Devfest 2014"
)

public class CultEndpoint {
	
	private final static Logger log = Logger.getLogger(CultEndpoint.class.getName());

	public CultEndpoint() {
		// TODO Auto-generated constructor stub
	}
	
	@ApiMethod(
			name="apiCultBackend.esponiCulturaJson", 
			httpMethod="post"
	)
	public CultPojo esponiCultura(@Named("limit") String limit){
		
		String jsonResult = null; 
		CultPojo pieceOfCult = new CultPojo();
		
		try{
			log.info("classe: " + CultEndpoint.class.getName() + " metodo remoto: esponiByCitta");
			String query = MessageKeyDispatcher.getString("sparql.rome.query", limit);
			
			SPARQLQueryServices queryService = new SPARQLQueryServicesImpl();
			
			jsonResult = queryService.callSparqlEndpoint(query);
			pieceOfCult.setQueryResult(jsonResult);
		}
		catch(SPARQLServiceException e){
			log.severe("Chiamata metodo remoto: esponiByCitta Errore dal servizio SPARQL DBPedia");
		}
		catch(Exception e){
			log.severe("Chiamata metodo remoto: esponiByCitta non terminata correttamente");
		}
		
		return pieceOfCult;
		
	}
	

}
