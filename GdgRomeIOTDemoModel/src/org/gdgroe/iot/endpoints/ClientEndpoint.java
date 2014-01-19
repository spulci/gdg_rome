package org.gdgroe.iot.endpoints;

import java.util.List;


import org.gdgrome.iot.model.dao.ClientDao;
import org.gdgrome.iot.model.entities.ClientBean;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

@Api(name = "clientendpoint", namespace = @ApiNamespace(ownerDomain = "gdgrome.org", ownerName = "gdgrome.org", packagePath = ""))
public class ClientEndpoint {
	
	public ClientEndpoint() {
		
	}
	
	@ApiMethod(name = "listClients")
	public List<ClientBean> listClientBeans(){
		List<ClientBean> clientBeanLst = null;
		ClientDao daoClient = new ClientDao();
		clientBeanLst = daoClient.fetchClientBeans();	
		return clientBeanLst;
	}
	
	@ApiMethod(name = "addClient")
	public void addClient(@Named("client")ClientBean bean){
		ClientDao daoClient = new ClientDao();
		daoClient.addClientBean(bean);
	}
	
	@ApiMethod(name = "removeClient")
	public void removeClient(@Named("id")long id){
		ClientDao daoClient = new ClientDao();
		daoClient.removeClientBeanById(id);
	}
	
	

}
