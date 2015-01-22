/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dependencies;

import dependencies.model.Relationship;
import dependencies.model.AtributesCollection;
import dependencies.model.Decompositions;
import dependencies.model.FunctionalDependency;
import dependencies.model.utilities.Matrix;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Marcel
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        XClosure closure = new XClosure();
        
        Relationship relation =  new Relationship();
        //lista 3 - 
        /*relation.addDependency(new FunctionalDependency("A", "B"));
        relation.addDependency(new FunctionalDependency("B,C", "D,E"));
        relation.addDependency(new FunctionalDependency("A,E,F", "G"));
        relation.addDependency(new FunctionalDependency("B,D,E", "F"));*/
        
        //exemplo 3 slide aula 7 e 8
        /*relation.addDependency(new FunctionalDependency("A", "B,C"));
        relation.addDependency(new FunctionalDependency("B", "C"));
        relation.addDependency(new FunctionalDependency("A", "B"));
        relation.addDependency(new FunctionalDependency("A,B", "C"));
        relation.addDependency(new FunctionalDependency("A,C", "D"));*/
        
        //lista 3 - ex 8
        /*relation.addDependency(new FunctionalDependency("A,B", "C"));
        relation.addDependency(new FunctionalDependency("C", "A"));
        relation.addDependency(new FunctionalDependency("B,C", "D"));
        relation.addDependency(new FunctionalDependency("A,C,D", "B"));
        relation.addDependency(new FunctionalDependency("B,E", "C"));
        relation.addDependency(new FunctionalDependency("C,E", "A,F"));
        relation.addDependency(new FunctionalDependency("C,F", "B,D"));
        relation.addDependency(new FunctionalDependency("D", "E,F"));
        relation.printRelations();*/
        
        /*relation.addDependency(new FunctionalDependency("A", "B"));
        relation.addDependency(new FunctionalDependency("B", "C"));
        //relation.addDependency(new FunctionalDependency("A,C,D", "B"));
        relation.addDependency(new FunctionalDependency("C", "D"));
        relation.addDependency(new FunctionalDependency("A", "D"));
        relation.printRelations();*/
        
        relation.addDependency(new FunctionalDependency("B", "C"));
        relation.addDependency(new FunctionalDependency("A", "C"));
        relation.addDependency(new FunctionalDependency("C", "D"));
        relation.addDependency(new FunctionalDependency("DE", "C"));
        relation.addDependency(new FunctionalDependency("CE", "A"));
        System.out.println("");
        RelationshipReduction rr = new RelationshipReduction(relation);
        
        Relationship r = rr.getReducedRelationship();
        r.printRelations();
        System.out.println("");
        
        Decompositions dcmp = new Decompositions();
        
        
        DecompositionTable dt = new DecompositionTable(r, dcmp);
        dt.printTable();
        System.out.println("");
        System.out.println(dt.hasInformationLoss());
        System.out.println("");
        dt.printTable();
        /*rr = new RelationshipReduction(r);
        Relation r2 = rr.getReducedRelationship();
        r.printRelations();*/
        
        
        
        //closure.findXClosure(relation, "B,C");
        //System.out.println(closure.isInXClosure(new AtributesCollection("D")));
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
