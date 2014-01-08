package org.gdgrome.iot.model.dao.interfaces;

import java.util.List;

import org.gdgrome.iot.model.entities.ClientBean;

public interface IClientDao {

	public List<ClientBean> fetchClientBeans();
	public ClientBean fetchClientBeanById(long id);
	public ClientBean fetchClientBeanByRegistrationId(String id);
	public ClientBean fetchClientBeanByMessageId(String id);
	public void addClientBean(ClientBean entity);
	public void removeClientBeanById(long id);
}
