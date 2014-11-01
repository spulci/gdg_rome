package org.gdgrome.lab.gdgcultfest.endpoints;

import java.util.logging.Logger;

import javax.inject.Named;

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
			name="esponi", 
			httpMethod=HttpMethod.POST
	)
	public void esponiCultura(){
		try{
			log.info("classe: " + CultEndpoint.class.getName() + " metodo remoto: esponi");
		}
		catch(Exception e){
			log.severe("Chiamata metodo remoto: esponi non terminata correttamente");
		}
		
	}
	
	@ApiMethod(
			name="esponiByCitta", 
			httpMethod=HttpMethod.POST
	)
	public void esponiCultura(@Named @Nullable String citta){
		try{
			log.info("classe: " + CultEndpoint.class.getName() + " metodo remoto: esponiByCitta");
		}
		catch(Exception e){
			log.severe("Chiamata metodo remoto: esponiByCitta non terminata correttamente");
		}
	}

}
