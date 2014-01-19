package org.gdgrome.iot.endpoints;

import java.io.IOException;

import org.gdgrome.iot.model.dao.MessageDao;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;

@Api(name="messageEndpoint")
public class MessageEndpoint {

	public MessageEndpoint() {
		// TODO Auto-generated constructor stub
	}
	
	@ApiMethod(name = "send")
	public void sendMessage(@Named("message") String message) throws IOException{
		MessageDao daoMessage = new MessageDao();
		daoMessage.sendMessagge(message);	
	}

}
