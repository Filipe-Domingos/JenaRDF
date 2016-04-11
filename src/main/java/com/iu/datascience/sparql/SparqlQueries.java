package com.iu.datascience.sparql;


import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class SparqlQueries {

    //RDF FileName
    static String inputFileName = "family.rdf";
    public static void main(String[] args) {

        //Create the default model
        Model model = ModelFactory.createDefaultModel();

        //Read the file
        model.read(new RDFReader().read(inputFileName), "");

        //First Query
        String queryString = "PREFIX family: <http://family/>\n" +
                "PREFIX rel: <http://purl.org/vocab/relationship/>\n" +
                "SELECT ?grandparent\n" +
                "where {\n" +
                "\t?x rel:parentOf family:harriet .\n" +
                "\t?x rel:childOf ?grandparent .\n" +
                "}\n" +
                "ORDER BY ?grandparent\n";

        System.out.println("First Query Output");
        runQuery(queryString, model);

        //Second query
        queryString = "PREFIX family: <http://family/>\n" +
                "PREFIX rel: <http://purl.org/vocab/relationship/>\n" +
                "SELECT ?people\n" +
                "where {\n" +
                "\t{?x rel:childOf ?people .}\n" +
                "\tUNION {?x rel:siblingOf: ?people}\n" +
                "}\n" +
                "ORDER BY DESC(?people)\n";

        System.out.println("Second query output");
        runQuery(queryString, model);

    }

    public static void runQuery(String queryString, Model model) {

        Query query = QueryFactory.create(queryString);

        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        // Output query results
        ResultSetFormatter.out(System.out, results, query);

        // Important - free up resources used running the query
        qe.close();
    }
}
