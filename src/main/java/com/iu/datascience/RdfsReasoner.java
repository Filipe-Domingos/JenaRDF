package com.iu.datascience;


import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;

public class RdfsReasoner {

    //Defining files
    private static String NS = "urn:x-hp:eg/";
    private static String fnameschema = "rdfsDemoSchema.rdf";
    private static String fnameinstance = "rdfsDemoData.rdf";

    public static void main(String args[]) {
        Model schema = FileManager.get().loadModel(fnameschema);
        Model data = FileManager.get().loadModel(fnameinstance);
        InfModel infmodel = ModelFactory.createRDFSModel(schema, data);

        Resource colin = infmodel.getResource(NS+"colin");
        System.out.println("colin has types:");
        for (StmtIterator i = infmodel.listStatements(colin, RDF.type, 		(RDFNode)null); i.hasNext(); ) {
            Statement s = i.nextStatement();
            System.out.println(s); }

        Resource Person = infmodel.getResource(NS+"Person");
        System.out.println("\nPerson has types:");
        for (StmtIterator i = infmodel.listStatements(Person, RDF.type, (RDFNode)null); i.hasNext(); ) {
            Statement s = i.nextStatement();
            System.out.println(s);} }
}

