/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import chat.generalCRUD;
import java.util.ArrayList;

/**
 *
 * @author Alaa
 */
public interface request_inter extends generalCRUD<request>{
    
   @Override
     public int create(request element);
    
     @Override
    public int update(request element);
   
     @Override
    public int delete(request parameter);
    
      public int getReqcount();
   
    public request retrieve(request element);
    
    public ArrayList<request> get(request element);
}
