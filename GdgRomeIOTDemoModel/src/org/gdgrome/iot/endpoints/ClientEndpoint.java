package org.gdgrome.iot.endpoints;

import java.util.List;



import org.gdgrome.iot.model.dao.ClientDao;
import org.gdgrome.iot.model.entities.ClientBean;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.Key;

@Api(name = "clientendpoint")
public class ClientEndpoint {
	
	public ClientEndpoint() {
		
	}
	
	@ApiMethod(name = "clientendpoint.listClients", httpMethod="post")
	public List<ClientBean> listClientBeans(){
		List<ClientBean> clientBeanLst = null;
		ClientDao daoClient = new ClientDao();
		clientBeanLst = daoClient.fetchClientBeans();	
		return clientBeanLst;
	}
	
	@ApiMethod(name = "clientendpoint.addClient", httpMethod="post")
	public void addClient(ClientBean client){
		ClientDao daoClient = new ClientDao();
		daoClient.addClientBean(client);
	}
	
	@ApiMethod(name = "clientendpoint.removeClient", httpMethod="post")
	public void removeClient(Key id){
		ClientDao daoClient = new ClientDao();
		daoClient.removeClientBeanById(id);
	}
	
	

}
