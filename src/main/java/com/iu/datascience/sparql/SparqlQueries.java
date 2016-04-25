package com.iu.datascience.sparql;


import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;

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

        //ResultSetFormatter.out(System.out, results, query);

        for (; results.hasNext() ; ) {
            QuerySolution soln = results.nextSolution() ;
            RDFNode x = soln.get("family") ;       // Get a result variable by name.
            Resource r = soln.getResource("family") ; // Get a result variable - must be a resource
            Literal l = soln.getLiteral("family") ;   // Get a result variable - must be a literal

            System.out.println(x);
            System.out.println(r);
            System.out.println(l);
        }
        // Output query results


        // Important - free up resources used running the query
        qe.close();
    }
}
