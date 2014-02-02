package org.gdgrome.iot.endpoints;

import java.util.List;



import java.util.logging.Logger;

import org.gdgrome.iot.model.dao.ClientDao;
import org.gdgrome.iot.model.entities.ClientBean;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.Key;

@Api(name = "clientEndpoint")
public class ClientEndpoint {
	
	private static final Logger log = Logger.getLogger(ClientEndpoint.class.getName());
	
	public ClientEndpoint() {
		
	}
	
	@ApiMethod(name = "operation.listClients", httpMethod="post")
	public List<ClientBean> listClientBeans(){
		log.info("Endpoint call: operation.listClients" );
		
		List<ClientBean> clientBeanLst = null;
		ClientDao daoClient = new ClientDao();
		clientBeanLst = daoClient.fetchClientBeans();	
		return clientBeanLst;
	}
	
	@ApiMethod(name = "operation.addClient", httpMethod="post")
	public void addClient(ClientBean client){
		log.info("Endpoint call: operation.addClient" );
		
		ClientDao daoClient = new ClientDao();
		daoClient.addClientBean(client);
	}
	
	@ApiMethod(name = "operation.removeClient", httpMethod="post")
	public void removeClient(Key id){
		log.info("Endpoint call: operation.removeClient" );
		
		ClientDao daoClient = new ClientDao();
		daoClient.removeClientBeanById(id);
	}
	
	

}
