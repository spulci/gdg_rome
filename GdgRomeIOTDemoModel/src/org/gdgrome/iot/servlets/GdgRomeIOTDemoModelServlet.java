package org.gdgrome.iot.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

import org.gdgrome.iot.model.dao.ClientDao;
import org.gdgrome.iot.model.entities.ClientBean;

@SuppressWarnings("serial")
public class GdgRomeIOTDemoModelServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		//Se lo usate crea uno stub e fa un test....attenzione funziona solo al primo lancio
		//del resto il registrationID è univoco...e se lo lanciate più volte la delete fallisce
		//perché non da uno uniqueresult quando fa la fetch per trovare un oggetto
		//attached al persistent layer
		
		ClientBean bean = new ClientBean();
		bean.setMessageId("abnd343543443");
		bean.setRegistrationId("032jdbfew223");
		ClientDao dao = new ClientDao();
		
		//dao.addClientBean(bean);
		List<ClientBean> beans = dao.fetchClientBeans();
		int size = beans.size();
		for(ClientBean b:beans)
		System.out.println(b.getMessageId());
		dao.removeClientByRegistrationId(bean.getRegistrationId());
		
	}
	
	public void init(){
		//Qui inizializzerò il bootstrap di WELD quando avrò finito il logging
	}
}
