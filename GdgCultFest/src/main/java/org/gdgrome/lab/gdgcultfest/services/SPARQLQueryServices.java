package org.gdgrome.lab.gdgcultfest.services;

public interface SPARQLQueryServices {

	public final static String DBPEDIA_ENDPOINT = "http://dbpedia.org/sparql";
	
	public String callSparqlEndpoint(String sparqlQuery, String limit);
}
