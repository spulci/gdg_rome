package org.gdgrome.iot.model.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import org.gdgrome.iot.common.MessageKeyDispatcher;
import org.gdgrome.iot.model.dao.interfaces.IMessageDao;
import org.gdgrome.iot.model.entities.ClientBean;
import org.gdgrome.iot.model.entities.MessageDataBean;
import org.gdgrome.iot.model.factory.IOTServiceSingleton;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;




public class MessageDao implements IMessageDao {
	
	public final static String API_KEY = MessageKeyDispatcher.getString("Message.api.key"); //$NON-NLS-1$

	public MessageDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendMessagge(String message)
			throws IOException {
		
		Sender sender = new Sender(API_KEY);
		ClientDao daoClient = new ClientDao();
		
	    // create a MessageData entity with a timestamp of when it was
	    // received, and persist it
	    MessageDataBean messageDataBean = new MessageDataBean();
	    messageDataBean.setMessage(message);
	    messageDataBean.setTimestamp(System.currentTimeMillis());
	    EntityManager mgr = IOTServiceSingleton.makeInstance().createEntityManager();
	    try {
	      mgr.persist(messageDataBean);
	    } finally {
	      mgr.close();
	    }
	    // ping a max of 10 registered devices
	    List<ClientBean> clients = daoClient.fetchClientBeans();
	    
	    for (ClientBean beanClient : clients) {
	      doSendViaGcm(message, sender, beanClient);
	    }
	}
	
	private static Result doSendViaGcm(String message, Sender sender,
			ClientBean clientBean) throws IOException {
		    // Trim message if needed.
			ClientDao daoClient = new ClientDao();
			
		    if (message.length() > 1000) {
		      message = message.substring(0, 1000) + "[...]"; //$NON-NLS-1$
		    }

		    // This message object is a Google Cloud Messaging object, it is NOT 
		    // related to the MessageData class
		    Message msg = new Message.Builder().addData("message", message).build(); //$NON-NLS-1$
		    Result result = sender.send(msg, clientBean.getRegistrationId(),
		        5);
		    if (result.getMessageId() != null) {
		      String canonicalRegId = result.getCanonicalRegistrationId();
		      if (canonicalRegId != null) {
		        daoClient.removeClientByRegistrationId(clientBean.getRegistrationId());
		        clientBean.setRegistrationId(canonicalRegId);
		        daoClient.addClientBean(clientBean);
		      }
		    } else {
		      String error = result.getErrorCodeName();
		      if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
		        daoClient.removeClientByRegistrationId(clientBean.getRegistrationId());
		      }
		    }

		    return result;
		  }

}
