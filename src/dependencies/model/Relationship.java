/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencies.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Marcel
 */
public class Relationship implements Cloneable{
    
    private Set<FunctionalDependency> dependencies = new LinkedHashSet<>();
        
    public Relationship() {
    }

    public Relationship(Set<FunctionalDependency> dependencies) {
        this.dependencies = dependencies;
    }

    public Set<FunctionalDependency> getDependencies() {
        return dependencies;
    }

    public void addDependency(FunctionalDependency dependency) {
        dependencies.add(dependency);
    }
   
    public void addDependencies(Collection<? extends FunctionalDependency> dependencies) {
        this.dependencies = (Set) dependencies;
    }
     
    public void printRelations(){
        if(dependencies!=null)
            for(FunctionalDependency fd:dependencies){
                fd.printDependency();
                //System.out.println("");
            }
        else
            System.out.println("Não há dependências");
    } 

    public Set<String> getAtributes() {
         Set<String> atributes = new LinkedHashSet<>();
         
        for(FunctionalDependency fd: dependencies)
            atributes.addAll(fd.getAllAtributes());
        
        return atributes;
    }
    
    public void remove(FunctionalDependency fd){
        dependencies.remove(fd);
    }
    
    @Override
    public Relationship clone() throws CloneNotSupportedException{
        Relationship clone = (Relationship) super.clone();
        
        clone.dependencies = new LinkedHashSet<>(this.dependencies);
        return clone; 
    }
}
