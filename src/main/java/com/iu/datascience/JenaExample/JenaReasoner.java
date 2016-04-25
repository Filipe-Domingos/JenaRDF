package com.iu.datascience.JenaExample;


import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;

import java.util.List;

public class JenaReasoner {

    static String student    = "http://jenaExamples/JohnSmith";
    static String teacher    = "http://jenaExamples/Andre";
    static String subject    = "http://jenaExamples/DataSemantics";

    public static void main(String[] args) {

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // create the student resource
        Resource studentRec = model.createResource(student);

        //Create the teacher resource
        Resource teacherRec = model.createResource(teacher);

        //Create the subject resource
        Resource subjectRec = model.createResource(subject);

        Property enrolledInClass = ResourceFactory.createProperty(studentRec.getNameSpace(), "enrolledInClass");

        Property teaches = ResourceFactory.createProperty(teacherRec.getNameSpace(), "teaches");

        //Add properties
        studentRec.addProperty(enrolledInClass, subjectRec);

        teacherRec.addProperty(teaches, subjectRec);

        //Adding a simple rule using the reasoner

        List<Rule> rules = Rule.parseRules("[ruleHasStudent: (?student <http://jenaExamples/enrolledInClass> ?subject) " +
                "(?teacher <http://jenaExamples/teaches> ?subject) -> (?teacher <http://jenaExamples/hasStudent> ?student)]");

        Reasoner reasoner = new GenericRuleReasoner(rules);

        InfModel infModel = ModelFactory.createInfModel( reasoner, model );

        infModel.write(System.out, "N-TRIPLES");


        //SPARQL query to fetch JohnSmith teacher from the hasStudent property

        String queryString = "PREFIX url: <http://jenaExamples/>\n" +
                "SELECT ?student \n" +
                "where {\n" +
                "\t?student url:hasStudent url:JohnSmith .\n" +
                "}\n";

        runQuery(queryString, infModel);
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
