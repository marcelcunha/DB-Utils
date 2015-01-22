/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependencies.model.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author Marcel
 * @param <T>
 */
public class Matrix <T>{
    
    private List<List<T>> matrix;
    private final int rows,
                      columns;
    
    public static class MyArrayList<T> extends ArrayList<T> {

        /**
         *
         * @param input
         * @return
         */
        private final int size;

        public MyArrayList(int size) {
            this.size = size;
        }
        
        @Override
        public boolean add(T input) {
            if (this.size() < size) {
                return super.add(input);
            } else {
                return false;
            }
        }
    }
    
    public Matrix(int row, int column) {
         
        matrix = new MyArrayList<>(row);
        
        for(int i=0; i<row; i++){
            matrix.add(i, new MyArrayList<T>(column));
        }
            
        this.rows = row;
        this.columns = column;
    }
    
    public void add(int rowIndex, int columnIndex, T value){
        matrix.get(rowIndex).add(columnIndex, value);  
    }
    
    public void addRow(int rowIndex, T[] array){
        if(rowIndex!=array.length)
            throw new ArrayIndexOutOfBoundsException("O tamanho da linha é "
                    + "diferente do tamanho do array de parametro");
         matrix.get(rowIndex).addAll(Arrays.asList(array));
    }
    
    public void addColumn(int columnIndex, T[] array){
        if(columnIndex!=array.length)
            throw new ArrayIndexOutOfBoundsException("O tamanho da coluna é "
                    + "diferente do tamanho do array de parametro");
        
        for(int i=0; i<rows; i++){
            //matrix[i][columnIndex]=array[i];
            matrix.get(i).add(columnIndex, array[i]);
        }
    }
    
    public T getValue(int rowIndex, int columnIndex){
        return matrix.get(rowIndex).get(columnIndex);
    }
    
    public void getRow(int index, T[] array) {
        if(index > columns)
            throw new ArrayIndexOutOfBoundsException("Índicce informado é maior"
                    + "que o tamanho da coluna");
        
        matrix.get(index).toArray(array);
    }
    
    public List<T> getRow(int index){
        return matrix.get(index);
    }
    
    public void getColumn(int index, T[] array){
        if(index > rows)
            throw new ArrayIndexOutOfBoundsException("Índice informado é maior "
                    + "que o tamanho da linha");
                
        for(int i=0; i<rows; i++){
            array[i] = matrix.get(i).get(index);
        }
    }
    
    public List<T> getColumn(int index){
        final List<T> list = new MyArrayList<>(rows);
        for(int i=0; i<rows; i++)
            list.add(matrix.get(i).get(index));
        return list; 
    }
    
    public void printMatrix(){
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                System.out.print(matrix.get(i).get(j)+"\t");
            }
            System.out.println("");
        }
    }
    
    public boolean containsInColumn(int columnIndex, T element){
        return getColumn(columnIndex).contains(element);
    }
    
    public boolean containsInRow(int rowIndex, T element){
        return matrix.get(rowIndex).contains(element);
    }
}
