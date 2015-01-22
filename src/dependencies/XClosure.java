/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencies;

import dependencies.model.Relationship;
import dependencies.model.AtributesCollection;
import dependencies.model.FunctionalDependency;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcel
 */
public final class XClosure {
    final private Set<String> xClosure = new TreeSet<>();

    public XClosure() {
    }

    public XClosure(Relationship relations, AtributesCollection atributes) {
        findXClosure(relations, atributes);
    }
    
    
    public Set<String> findXClosure(Relationship relations, AtributesCollection atributes){
        try {
            Relationship  relationsClone = relations.clone();
            Set<FunctionalDependency> dependencies = relationsClone.getDependencies();
            
            int flag = -1;
            
            xClosure.addAll(atributes.getAtributes());//inicialmente o fecho assume o valor de atributes
            
            while(xClosure.size() < relationsClone.getAtributes().size() //enquanto o fecho não possui todos atributos...
                    &&!dependencies.isEmpty()//... e ainda há dependencias para ser analisadas...
                    &&flag!=dependencies.size()//...e alguma dependencia foi usada na última iteração
                    ){//o loop continuará sendo executado
                flag=dependencies.size();
                for(Iterator<FunctionalDependency> it = dependencies.iterator(); it.hasNext();){
                    FunctionalDependency fd = it.next();
                    
                    if(xClosure.containsAll(fd.getXAtributes())){//se o lado X de uma relação está contido no fecho...
                        xClosure.addAll(fd.getYAtributes());//... o lado Y será adicionado ao mesmo
                        it.remove();
                    }
                }
                
            }
            return xClosure;            
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(XClosure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void clear(){
        xClosure.clear();
    }
    
    public Set<String> findXClosure(Relationship relations, String atb){
        AtributesCollection atributes = new AtributesCollection(atb);
        Set<FunctionalDependency> dependencies = relations.getDependencies();
        
        int flag = -1;
        
        xClosure.addAll(atributes.getAtributes());//inicialmente o fecho assume o valor de atributes
        
        while(xClosure.size() < relations.getAtributes().size() //enquanto o fecho não possui todos atributos... 
                &&!dependencies.isEmpty()//... e ainda há dependencias para ser analisadas... 
                &&flag!=dependencies.size()//...e alguma dependencia foi usada na última iteração
        ){//o loop continuará sendo executado
            flag=dependencies.size();
            for(Iterator<FunctionalDependency> it = dependencies.iterator(); it.hasNext();){
                FunctionalDependency fd = it.next();
                
                if(xClosure.containsAll(fd.getXAtributes())){//se o lado X de uma relação está contido no fecho...
                    xClosure.addAll(fd.getYAtributes());//... o lado Y será adicionado ao mesmo
                    it.remove();
                }
            }          
        }
        return xClosure;
    }
    
    public boolean isInXClosure(AtributesCollection atributes) throws Exception{
        if(xClosure.isEmpty()){//lança excessão caso o fecho não tenha sido inicializado
            throw new Exception("Fecho não foi inicializado!");
        }
    
        if(xClosure.containsAll(atributes.getAtributes()))//se o atributo procurado estiver no fecho
            return true;       //...retorna verdadeiro
        return false;       //caso contrário, retorna falso
    }
}
