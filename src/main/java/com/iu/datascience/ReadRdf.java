package com.iu.datascience;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.InputStream;


public class ReadRdf {

    static final String inputFileName  = "Resturant.rdf";

    public static void main (String args[]) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, "");

        // write it to standard out
        model.write(System.out, "N-TRIPLE");
    }
}
