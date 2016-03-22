package com.iu.datascience.com.iu.datascience.inference;


import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.util.FileManager;

import java.io.InputStream;
import java.util.Iterator;

public class Validate {

    static final String inputFileName  = "dttest3.nt";
    public static void main(String[] args) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }

        // read the RDF/XML file
        model.read(in, "", "N-TRIPLE");

        // write it to standard out
        model.write(System.out);


        Model data = FileManager.get().loadModel(inputFileName);

        InfModel infmodel = ModelFactory.createRDFSModel(data);

        ValidityReport validity = infmodel.validate();

        if (validity.isValid()) {
            System.out.println("OK");
        } else {
            System.out.println("Conflicts");
            for (Iterator i = validity.getReports(); i.hasNext(); ) {
                System.out.println(" - " + i.next());
            }
        }
    }
}
