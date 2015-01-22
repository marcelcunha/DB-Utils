/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencies;

import dependencies.model.AtributesCollection;
import dependencies.model.Decompositions;
import dependencies.model.FunctionalDependency;
import dependencies.model.Relationship;
import dependencies.model.utilities.Matrix;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Marcel
 */
public class DecompositionTable {
    private final Matrix<Boolean> table;
    private final List<String> atributes;
    private final List<FunctionalDependency> dependencies;
    private final Map<String, Integer> indexes = new HashMap<>();
    private List<AtributesCollection> descompositions;
    
    /**
     * 
     * @param relation 
     * @param dcmp 
     */
    public DecompositionTable(Relationship relation, Decompositions dcmp) {
        table =  new Matrix<>(relation.getDependencies().size(), 
                        relation.getAtributes().size());//intancia a classe Matrix com a quantidade de linha e colunas
        atributes = new ArrayList<>(relation.getAtributes());
        dependencies = new ArrayList<>(relation.getDependencies());
        //this.descompositions = new ArrayList<>(dcmp.getDecompositions());
        
        for(int i=0; i<dependencies.size(); i++){
           for(int j=0; j<atributes.size(); j++){
               if(dependencies.get(i).getAllAtributes().contains(
                       atributes.get(j))){
                   table.add(i, j, Boolean.TRUE);
               }
               else{
                   table.add(i, j, Boolean.FALSE);
               }
           }
       }
        fillIndexes();
    }
    
    /**Mapeia os atributos com seus índices na Lista atribute*/
    private void fillIndexes(){
        for(String atribute: atributes)
            indexes.put(atribute, atributes.indexOf(atribute));
    }
    
    /**
     * 
     * @return 
     */
    public Matrix<Boolean> getTable() {
        return table;
    }
    
    public boolean hasInformationLoss(){
       
        for(int i=0; i<dependencies.size(); i++){
           boolean[] array =  getArray(dependencies.get(i).getXSide());
           
           int columnIndex = indexes.get(getAtribute(
                                        dependencies.get(i).getYAtributes()));
           
           for(int j=0; j<array.length; j++){
               if(array[j]==true)
                   table.add(j, columnIndex, Boolean.TRUE);
           } 
           if(isCompleteRow(i))
               return false;
        }
        
        return true;
    }
    
    private String getAtribute(Set<String> atb){
        if(atb.size()>1)
            System.out.println("Há mais de um atributo no lado Y");
        return new ArrayList<>(atb).get(0);
    }
    
    /**Imprime a tabela de decomposição usando a notação de nossa querida 
     * professora de banco de dados Mirella Junqueira.
     */
    public void printTable(){
        int length= - 1,
            iSize = dependencies.size(),
            jSize = atributes.size();
        
        for(String atb : atributes){
            if(atb.length()>length)
                length = atb.length();
        }
        System.out.printf("%-"+((int)Math.log10(iSize)+5)+"s","");    
        for(String atb : atributes ){
             System.out.printf("%"+length+"s%-7s",atb,"");
        }
           
        System.out.println("");
        for(int i=0; i<iSize; i++){
            
            for(int j=0; j<jSize; j++){
                if(j==0)
                System.out.printf("%"+(int)Math.log10(iSize)+1+"d%-4s", i,"");

                if(table.getValue(i, j))
                    System.out.printf("A%2d%-7s", j+1,"");
                else
                    System.out.printf("B%d%d%-7s", i+1,j+1,"");
            }
            System.out.println("");
        }    
    }
    
    private boolean[] getArray(AtributesCollection atb){
        //boolean[] array = fillArry(atributes.size());
        boolean[] array = new boolean[dependencies.size()];
        
        for(int i =0; i<array.length; i++)
            array[i]=true;
                
        
        for(String atribute : atb.getAtributes()){
            int index = indexes.get(atribute);
            
            for(int i=0; i<array.length; i++){
                array[i] = array[i]&&
                        table.getColumn(index)//encontra 
                                .get(i);
            }
            indexes.get(atribute);
        }
        
        return array;
    }
    
    /**Verifica se há uma linha da tabela formada somente por valores verdadeiros
     * @param index índice da linha a ser verificada
     * @return boolean - retorna true se existir, falso caso contrário
     */
    private boolean isCompleteRow(int index){
       
        for (Boolean row : table.getRow(index)) {//percorre cada elemento da linha
            if (row != true) { //se for falso entra a na condiçãoe retorna falso
                return false;
            }
        }
        
        return true; //se não verificar valores falsos retorna verdadeiro
    }  
}
