package com.iu.datascience.reasoner;


import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDFS;

public class ReasonerExample {

    public static void main(String[] args) {
        String NS = "urn:x-hp-jena:eg/";

// Build a trivial example data set
        Model rdfsExample = ModelFactory.createDefaultModel();
        Property p = rdfsExample.createProperty(NS, "p");
        Property q = rdfsExample.createProperty(NS, "q");
        rdfsExample.add(p, RDFS.subPropertyOf, q);
        rdfsExample.createResource(NS+"a").addProperty(p, "foo");

        //rdfsExample.write(System.out);

        InfModel infModel = ModelFactory.createRDFSModel(rdfsExample);

        //infModel.write(System.out);
        Resource resource = infModel.getResource(NS + "a");
        System.out.println(resource.getProperty(q));
    }
}
