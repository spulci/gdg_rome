package org.gdgrome.lab.gdgcultfest.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.gdgrome.lab.gdgcultfest.exceptions.SPARQLServiceException;
import org.gdgrome.lab.gdgcultfest.services.SPARQLQueryServices;

import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class SPARQLQueryServicesImpl implements SPARQLQueryServices {
	
	

	public SPARQLQueryServicesImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String callSparqlEndpoint(String sparqlQuery) throws SPARQLServiceException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			
			// La parte commentata non può girare su app engine perché Apache Jena utilizza il transport http di Java SE
			// che è considerata una classe riservata su AppEngine e che non può essere chiamata sull'infrastruttura di GAE
			
			//Query query = QueryFactory.create(sparqlQuery);
			//ARQ.getContext().setTrue(ARQ.useSAX);
			//QueryExecution qexec = QueryExecutionFactory.sparqlService(DBPEDIA_ENDPOINT, query);
			
			//Possiamo però fare una chiamata HTTP a basso livello invocando l'endpoint come un servizio REST
			HttpURLConnection connection = (HttpURLConnection) new URL(DBPEDIA_ENDPOINT + "?query=" +URLEncoder.encode(sparqlQuery, "UTF-8")).openConnection();
			connection.setRequestProperty("Accept", "application/sparql-results+xml");
			InputStream response = connection.getInputStream();
			
			//utilizziamo JENA solo per parsare il risultato
			ResultSet rs = ResultSetFactory.fromXML(response);
			//ResultSet rs = qexec.execSelect(); 
			
			//e per parsarlo rapidamente
			ResultSetFormatter.outputAsJSON(baos, rs);
		}
		catch(Exception ex) {
			throw new SPARQLServiceException(ex);
		}
		finally{
			baos.close();
		}
		return baos.toString();
	}

}
