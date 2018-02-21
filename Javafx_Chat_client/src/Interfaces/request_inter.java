/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import DTOs.request;
import Interfaces.generalCRUD;
import java.util.ArrayList;

/**
 *
 * 
 * @author Freeware Sys
 */
public interface request_inter extends generalCRUD<request>{
    
   @Override
     public int create(request element);
    
     @Override
    public int update(request element);
   
     @Override
    public int delete(request parameter);
    
    /**
     *
     * @return
     */
    public int getReqcount();
   
    public request retrieve(request element);
    
    /**
     *
     * @param element
     * @return
     */
    public ArrayList<request> get(request element);
}
