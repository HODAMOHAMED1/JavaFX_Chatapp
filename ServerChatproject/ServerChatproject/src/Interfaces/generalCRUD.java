/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author 3alilio
 * @param <T>
 */

public interface generalCRUD<T> {
    
    public int create(T element);
    
    public int update(T element);
    
    public T retrieve(T element);

    public int delete(T element);
            
}
