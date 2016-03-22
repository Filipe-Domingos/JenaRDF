package com.iu.datascience;


import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDFS;

public class ReasonerExamples {

    public static void main(String[] args) {

        String NS = "urn:x-hp-jena:eg/";

        //Build a trivial example data set
        Model rdfsExample = ModelFactory.createDefaultModel();
        Property p = rdfsExample.createProperty(NS, "p");
        Property q = rdfsExample.createProperty(NS, "q");
        rdfsExample.add(p, RDFS.subPropertyOf, q);
        rdfsExample.createResource(NS+"a").addProperty(q, "foo");

        InfModel inf = ModelFactory.createRDFSModel(rdfsExample);

        Resource a = inf.getResource(NS + "a");

        System.out.println("Printing a :" + a.listProperties().toString());


        rdfsExample.write(System.out);




    }
}
