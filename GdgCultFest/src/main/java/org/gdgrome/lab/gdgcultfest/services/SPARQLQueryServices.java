package org.gdgrome.lab.gdgcultfest.services;


//interfaccia endpoint
public interface SPARQLQueryServices {

	public final static String DBPEDIA_ENDPOINT = "http://dbpedia.org/sparql";
	
	public String callSparqlEndpoint(String sparqlQuery);
}
