package org.gdgrome.iot.model.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.gdgrome.iot.model.dao.interfaces.IClientDao;
import org.gdgrome.iot.model.entities.ClientBean;
import org.gdgrome.iot.model.factory.IOTServiceSingleton;

import com.google.appengine.api.datastore.Key;

public class ClientDao implements IClientDao {
	
	private final static String clientClassName = ClientBean.class.getName();
	private static final Logger log = Logger.getLogger(ClientDao.class.getName());


	public ClientDao() {
		// TODO Auto-generated constructor stub
		log.info("Create ClientDao instance");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientBean> fetchClientBeans() {
		log.info(ClientDao.class.getName() + ": fetchClientBeans()");
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		Query q = iotEntityManager.createQuery("select m from " + clientClassName + " m");
		List<ClientBean> clientBeans = q.getResultList();
		
		
		
		return clientBeans;
	}

	@Override
	public ClientBean fetchClientBeanById(long id) {
		log.info(ClientDao.class.getName() + ": fetchClientBeanById() with id = " + id);
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		ClientBean clientBean = iotEntityManager.find(ClientBean.class, id);
		iotEntityManager.close();
		
		return clientBean;
	}
	
	@Override
	public ClientBean fetchClientBeanByRegistrationId(String id) {
		log.info(ClientDao.class.getName() + ": fetchClientBeanByRegistrationId() with id = " + id);
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		Query q = iotEntityManager.createQuery("select m from " + clientClassName + " m where m.registrationId = :registrationIdValue");
		q.setParameter("registrationIdValue", id);
		
		ClientBean clientBean = (ClientBean) q.getSingleResult();
		
		return clientBean;
	}

	@Override
	public ClientBean fetchClientBeanByMessageId(String id) {
		log.info(ClientDao.class.getName() + ": fetchClientBeanByMessageId() with id = " + id);
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		Query q = iotEntityManager.createQuery("select m from " + clientClassName + " m where m.messageId = :messageIdValue");
		q.setParameter("messageIdValue", id);
		
		ClientBean clientBean = (ClientBean) q.getSingleResult();
		
		return clientBean;
	}

	@Override
	public synchronized void addClientBean(ClientBean entity) {
		log.info(ClientDao.class.getName() + ": addClientBean() with clientBean registration id = " + entity.getRegistrationId());
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		if (entity.getTimestamp() == 0L)
			entity.setTimestamp(System.currentTimeMillis());
		
		try{
			iotEntityManager.persist(entity);
			
		}
		catch(EntityExistsException e){
			log.severe("Entyty already exist with registration id = " + entity.getRegistrationId() + "   " + e.getMessage());
		}
		catch(Exception e){
			log.severe("Exeception for entity with registration id = " + entity.getRegistrationId() + "   " + e.getMessage());
		}
		finally{
			iotEntityManager.close();
		}
		

	}

	@Override
	public void removeClientBeanById(Key id) {
		log.info(ClientDao.class.getName() + ": removeClientBeanById() with pk id = " + id.getId());
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		try{
			ClientBean clientBean = iotEntityManager.find(ClientBean.class, id);
			iotEntityManager.remove(clientBean);
		}
		catch(IllegalArgumentException e){
			log.severe("Entity could be detached!" + e.getMessage());
		}
		catch(Exception e){
			log.severe("Generic exception! " + e.getMessage());
		}
		finally{
			iotEntityManager.close();
		}
		
	}
	
	@Override
	public void removeClientByRegistrationId(String id) {
		log.info(ClientDao.class.getName() + ": removeClientBeanById() with registration id = " + id);
		EntityManager iotEntityManager = IOTServiceSingleton.makeInstance().createEntityManager();
		
		try{
			ClientBean clientBean = fetchClientBeanByRegistrationId(id);
			iotEntityManager.remove(clientBean);
		}
		catch(IllegalArgumentException e){
			log.severe("Entity could be detached!" + e.getMessage());
		}
		catch(Exception e){
			log.severe("Generic exception! " + e.getMessage());
		}
		finally{
			iotEntityManager.close();
		}
		
	}

}
