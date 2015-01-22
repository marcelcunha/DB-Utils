/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencies;

import dependencies.model.Relationship;
import dependencies.model.AtributesCollection;
import dependencies.model.FunctionalDependency;
import dependencies.model.utilities.IterablePowerSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcel
 */
public class RelationshipReduction {
    private Relationship S = null;

    public RelationshipReduction(Relationship S) {
        this.S=S;
    }
    
    private void firstStep(){
        try {
            Relationship R = this.S.clone();
            
            for(FunctionalDependency fd:this.S.getDependencies()){//para cada dependencia funcional da relação fazer...    
                if(fd.getYSide().getAtributes().size()>1){//se ouver mais de um atributo no lado Y da dependencia
                    for(String ac : fd.getYAtributes())//cada atributo do lado Y da dependencia...
                        R.addDependency(new FunctionalDependency(fd.getXSide(), new AtributesCollection(ac)));//... é decomposto em uma nova dependencia...
                    R.remove(fd);//... e a dependencia inicial é eliminada da relação.
                }
            }
            this.S = R;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RelationshipReduction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void secondStep(){
        try {
            Relationship R = this.S.clone();
            try {
                Relationship S1 = R.clone();
                //Relations S2 = S.clone();
                
                for(FunctionalDependency fd:this.S.getDependencies()){//para cada dependencia funcional da relação...
                    
                    if(fd.getXAtributes().size()>1){//se ouver mais de um atributo no lado X da dependencia
                        List<Set<String>> xDecompositions = IterablePowerSet.powerset(fd.getXAtributes()); //gera todas possibilidades de decomposição da dependencia
                        FunctionalDependency[] candidates = new FunctionalDependency[xDecompositions.size()];
                        
                        
                        S1.remove(fd);//... e a dependencia inicial é eliminada da relação.
                        boolean flag = false; //flag que marca se já houve alguma dependência reduzida
                        
                        for(int i=0; i<candidates.length && flag==false; i++){//cada atributo do lado X da dependencia...
           
                            candidates[i] = new FunctionalDependency(xDecompositions.get(i), fd.getYAtributes());
                            S1.addDependency(candidates[i]);
                            
                            XClosure xClosure = new XClosure(S1, fd.getXSide());
                            try {
                                //passo 1 -
                                if (xClosure.isInXClosure(fd.getYSide())){
                                    xClosure.clear();
                                    xClosure.findXClosure(R, candidates[i].getXSide());
                                    
                                    //passo 2
                                    if(xClosure.isInXClosure(candidates[i].getYSide())){
                                        R.remove(fd);
                                        R.addDependency(candidates[i]);
                                        
                                        flag = true;
                                    }
                                    
                                }
                            } catch (Exception ex) {                                   
                                Logger.getLogger(RelationshipReduction.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(RelationshipReduction.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.S = R;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RelationshipReduction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void thirdStep(){
        try {
            Relationship R = this.S.clone();
            for(FunctionalDependency fd:this.S.getDependencies()){
                try {
                    Relationship S1 = R.clone(); 
                    S1.remove(fd); //remove a dependencia a ser verificada da relação
                    
                    XClosure xclosure = new XClosure(S1, fd.getXSide());
                    try {
                        if(xclosure.isInXClosure(fd.getYSide())){//se o atributo Y da dependencia estiver no fecho
                            R.remove(fd);//a mesma é removida da relação
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(RelationshipReduction.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(RelationshipReduction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.S=R;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RelationshipReduction.class.getName()).log(Level.SEVERE, null, ex);           
        }
    }
    
    /**Encontra o menor conjunto de dependências funcionais possivel na relação
     * @return Relationship conjunto irredutível 
     */
    public Relationship getReducedRelationship() {
        firstStep();
        secondStep();
        thirdStep();
        return S;
    }
    
    /** Verifica se a relação pode ser reduzida 
     * @param S-Relação a ser verificada
     * @return boolean - retorna verdadeiro se for possível reduzir
     *                   falso se não for possível
     */
    public boolean isReductible(Relationship S){
        getReducedRelationship();
        return !S.equals(this.S);
    }
    
}
