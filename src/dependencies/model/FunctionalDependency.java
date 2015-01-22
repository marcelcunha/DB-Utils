/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencies.model;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Marcel
 */
public class FunctionalDependency{
    private AtributesCollection X = new AtributesCollection(),
                                Y = new AtributesCollection();
    
    public FunctionalDependency() {
    }

    public FunctionalDependency(String xSide, String ySide) {
        this.X.setAtributes(xSide);
        this.Y.setAtributes(ySide);      
    }
    
   public FunctionalDependency(AtributesCollection xSide, AtributesCollection ySide) {
        this.X=xSide;
        this.Y=ySide;       
    }
    
   public FunctionalDependency(Set<String> xSide, Set<String> ySide) {
        this.X.setAtributes(xSide);
        this.Y.setAtributes(ySide);        
    }
    
   public void addDependency(String dependency){
        String[] array = dependency.split("->");
      
        this.X.setAtributes(array[0]);
        this.Y.setAtributes(array[1]);       
    } 
   
    public void addDependency(Set<String> xSide, Set<String> ySide){
        this.X.setAtributes(xSide);
        this.Y.setAtributes(ySide);       
    } 
    
    public void addDependency(String xSide, String ySide){
             
        this.X.setAtributes(xSide);
        this.Y.setAtributes(ySide);        
    } 
    
    /**Encontra todos os atributos da relação
     * @return Set<String> com todos atributos da relação
     */
    public Set<String> getAllAtributes() {
       Set<String> allAtributes = new TreeSet();
       
       allAtributes.addAll(X.getAtributes());
       allAtributes.addAll(Y.getAtributes());
       
       return allAtributes;
    }
    
    /**Retorna uma lista com todos atributos do lado X da dependência
     * @return List<String>-Lista em que cada nó é um atributo
     */
    public Set<String> getXAtributes() {
        if(X.getAtributes().isEmpty())
            System.out.println("Vazio");
        return X.getAtributes();
    }
    
    /**Retorna uma lista com todos atributos do lado Y da dependência
     * @return List<String>
     */
    public Set<String> getYAtributes() {
        if(X.getAtributes().isEmpty())
            System.out.println("Vazio");
        return Y.getAtributes();
    }
    
    /**Retorna uma lista com todos atributos do lado X da dependência
     * @return List<String>-Lista em que cada nó é um atributo
     */
    public AtributesCollection getXSide() {
        if(X.getAtributes().isEmpty())
            System.out.println("Vazio");
        return X;
    }
    
    /**Retorna uma lista com todos atributos do lado Y da dependência
     * @return List<String>
     */
    public AtributesCollection getYSide() {
        if(X.getAtributes().isEmpty())
            System.out.println("Vazio");
        return Y;
    }
    
    /**Imprime a dependência no console*/    
    public void printDependency(){
        
        for(String s: X.getAtributes()){
            System.out.printf("%s ", s);
        }
        
        System.out.print("\u2192 ");
       
        for(String s: Y.getAtributes()){
            System.out.printf("%s ", s);
        }
        
        System.out.println("");
    }
    
}
