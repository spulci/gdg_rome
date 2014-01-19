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
		//resp.setContentType("text/plain");
		//resp.getWriter().println("Hello, world");
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
		System.out.println("Init OK");
	}
}
