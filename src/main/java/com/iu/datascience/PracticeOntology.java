package com.iu.datascience;


import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

public class PracticeOntology {

    static final String JENA = "/Users/poj871/GitHubrepos/project/IUDataSemantics/";
    public static void main(String[] args) {
        OntModel m = ModelFactory.createOntologyModel();
        OntDocumentManager dm = m.getDocumentManager();
        //dm.addAltEntry( "http://www.eswc2006.org/technologies/ontology",
        //        "file:" + JENA + "src/main/resources/eswc-2006-09-21.rdf" );
        m.read( "http://www.eswc2006.org/technologies/ontology" );

        m.write(System.out);

        OntClass ontClass = m.createClass();
    }
}
