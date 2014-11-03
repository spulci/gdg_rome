package org.gdgrome.lab.gdgcultfest.services;

import java.io.IOException;

import org.gdgrome.lab.gdgcultfest.exceptions.SPARQLServiceException;


//interfaccia endpoint
public interface SPARQLQueryServices {

	public final static String DBPEDIA_ENDPOINT = "http://dbpedia.org/sparql";
	
	public String callSparqlEndpoint(String sparqlQuery) throws SPARQLServiceException, IOException;
}
