/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * 
 * @param <T>
 */

public interface generalCRUD<T> {
    
    /**
     *
     * @param element
     * @return
     */
    public int create(T element);
    
    /**
     *
     * @param element
     * @return
     */
    public int update(T element);
    
    /**
     *
     * @param element
     * @return
     */
    public T retrieve(T element);

    /**
     *
     * @param element
     * @return
     */
    public int delete(T element);
            
}
