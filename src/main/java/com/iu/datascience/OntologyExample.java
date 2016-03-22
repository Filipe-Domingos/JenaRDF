package com.iu.datascience;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

import java.io.InputStream;

public class OntologyExample {

    static final String inputFileName = "camera.owl";
    static String camNS = "http://www.xfront.com/owl/ontologies/camera/#";

    public static void main(String[] args) {

        // Create an empty in-memory ontology model
        OntDocumentManager mgr = new OntDocumentManager();
        OntModelSpec s = new OntModelSpec( OntModelSpec.RDFS_MEM );
        s.setDocumentManager( mgr );
        OntModel m = ModelFactory.createOntologyModel( s, null );

        m.setStrictMode(false);
        // use the FileManager to open the ontology from the filesystem
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found"); }

        // read the ontology file
        m.read( in, "" );

        // write it to standard out (RDF/XML)
        //m.write(System.out);

        OntClass camera = m.getOntClass( camNS + "Camera" );

        for (ExtendedIterator i = camera.listSubClasses(); i.hasNext(); ) {
            OntClass c = (OntClass) i.next();
            System.out.println( c.getLocalName() + " subclass of class 			Camera " );
        }

    }

}

