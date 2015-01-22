/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencies.model;

import java.util.Set;

/**
 *
 * @author Marcel
 */
public class Decompositions {
    private Set<AtributesCollection> decompositions;

    public Decompositions() {
    }

    public Decompositions(Set<AtributesCollection> decompositions) {
        this.decompositions = decompositions;
    }

    public Set<AtributesCollection> getDecompositions() {
        return decompositions;
    }

    public void setDecompositions(Set<AtributesCollection> decompositions) {
        this.decompositions = decompositions;
    }
    
    
}
