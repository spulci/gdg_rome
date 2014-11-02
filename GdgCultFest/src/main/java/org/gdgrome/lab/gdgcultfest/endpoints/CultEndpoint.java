package org.gdgrome.lab.gdgcultfest.endpoints;

import java.util.Properties;
import java.util.logging.Logger;

import javax.inject.Named;

import org.gdgrome.lab.gdgcultfest.beans.CultPojo;
import org.gdgrome.lab.gdgcultfest.common.MessageKeyDispatcher;
import org.gdgrome.lab.gdgcultfest.exceptions.SPARQLServiceException;
import org.gdgrome.lab.gdgcultfest.services.SPARQLQueryServices;
import org.gdgrome.lab.gdgcultfest.services.impl.SPARQLQueryServicesImpl;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Nullable;

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
			name="apiCultBackend.esponiByCitta", 
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
	
	public static void main(String args[]){
		Properties props = new Properties();
        props.setProperty("url", "PREFIX dbpclass: <http://dbpedia.org/class/yago/ArtMuseumsAndGalleriesInRome>PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>PREFIX grs: <http://www.georss.org/georss/>SELECT ?resource ?abstractEN ?abstractIT WHERE { ?resource a <http://dbpedia.org/class/yago/ArtMuseumsAndGalleriesInRome> . ?resource dbpedia-owl:abstract ?abstractEN . ?resource dbpedia-owl:abstract ?abstractIT . FILTER(lang(?abstractEN) = 'en' AND lang(?abstractIT) = 'it' ) } ORDER BY ?resource LIMIT {0}");
        try{
        	props.store(System.out, null);
        }
        catch(Exception e){
        	
        }
        
	}

}
