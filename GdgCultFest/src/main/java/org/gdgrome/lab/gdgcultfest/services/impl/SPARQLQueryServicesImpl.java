package org.gdgrome.lab.gdgcultfest.services.impl;

import java.io.ByteArrayOutputStream;

import org.gdgrome.lab.gdgcultfest.services.SPARQLQueryServices;

import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class SPARQLQueryServicesImpl implements SPARQLQueryServices {
	
	

	public SPARQLQueryServicesImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String callSparqlEndpoint(String sparqlQuery) {
		Query query = QueryFactory.create(sparqlQuery);
		ARQ.getContext().setTrue(ARQ.useSAX);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(DBPEDIA_ENDPOINT, query);
		ResultSet rs = qexec.execSelect();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		ResultSetFormatter.outputAsJSON(baos, rs);
		
		return baos.toString();
	}

}
