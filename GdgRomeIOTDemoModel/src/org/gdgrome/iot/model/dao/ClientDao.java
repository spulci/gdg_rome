package org.gdgrome.iot.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.gdgrome.iot.model.dao.interfaces.IClientDao;
import org.gdgrome.iot.model.entities.ClientBean;
import org.gdgrome.iot.model.factory.IOTServiceSingleton;

public class ClientDao implements IClientDao {
	
	private final static String clientClassName = ClientBean.class.getName();

	public ClientDao() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientBean> fetchClientBeans() {
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		Query q = iotEntityManager.createQuery("select m from " + clientClassName + " m");
		List<ClientBean> clientBeans = q.getResultList();
		
		return clientBeans;
	}

	@Override
	public ClientBean fetchClientBeanById(long id) {
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		ClientBean clientBean = iotEntityManager.find(ClientBean.class, id);
		iotEntityManager.close();
		
		return clientBean;
	}
	
	@Override
	public ClientBean fetchClientBeanByRegistrationId(String id) {
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		Query q = iotEntityManager.createQuery("select m from " + clientClassName + " m where t.registrationId = :registrationIdValue");
		q.setParameter("registrationIdValue", id);
		
		ClientBean clientBean = (ClientBean) q.getSingleResult();
		
		return clientBean;
	}

	@Override
	public ClientBean fetchClientBeanByMessageId(String id) {
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		Query q = iotEntityManager.createQuery("select m from " + clientClassName + " m where t.messageId = :messageIdValue");
		q.setParameter("messageIdValue", id);
		
		ClientBean clientBean = (ClientBean) q.getSingleResult();
		
		return clientBean;
	}

	@Override
	public synchronized void addClientBean(ClientBean entity) {
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		iotEntityManager.persist(entity);
		iotEntityManager.close();

	}

	@Override
	public void removeClientBeanById(long id) {
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		try{
			ClientBean clientBean = iotEntityManager.find(ClientBean.class, id);
			iotEntityManager.remove(clientBean);
		}
		finally{
			iotEntityManager.close();
		}
		
	}

}
