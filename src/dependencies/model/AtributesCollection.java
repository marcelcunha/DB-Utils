/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencies.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Marcel
 */
final public class AtributesCollection { 
    final private Set<String> atributes = new TreeSet();

    public AtributesCollection() {
    }

    public AtributesCollection(String atributes) {
        this.atributes.addAll(splitAtributes(atributes));
    }
    
    /**Método interno que auxilia a formatação das dependências funcionais
     * 
     * @param atributesStr
     * @return 
     */
    protected List<String> splitAtributes(String atributesStr){
        atributesStr = atributesStr.replaceAll(" ", "");//remove os espaços " " da string
        String[] atributes = atributesStr.split(","); //separa todos atributos
        return Arrays.asList(atributes);
    }

    public Set<String> getAtributes() {
        if(atributes.isEmpty()){
            System.out.println("Não há atributos na coleção");
        }
        return atributes;
    }
    
    public List<String> getAtributesList(){
        if(atributes.isEmpty()){
            System.out.println("Não há atributos na coleção");
        }
        return new ArrayList<>(atributes);
    }

    public void setAtributes(String atributes) {
        this.atributes.addAll(splitAtributes(atributes));
    }
    
    public void setAtributes(Collection<? extends String> atributes) {
        this.atributes.addAll(atributes);
    }
}
