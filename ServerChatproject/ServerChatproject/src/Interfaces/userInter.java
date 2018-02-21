/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import DTOs.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 3alilio
 */
public interface userInter extends generalCRUD<User>{
    /**
     *
     * @param element
     * @return
     */
    @Override
     public int create(User element);
    
     @Override
    public int update(User element);
   
     @Override
    public int delete(User element);
    
    public User retrieve(User element);

    public ResultSet selectAll();
    
    public int selectFemales();
    
    public int selectMales();

    public int selectOnline();
    
    public int selectOffline();
    
    public int selectBusy();
    
    public int selectAway();
    
    public int selectAvaible();

    public int selectGenderAgeGreater(String s,int age);
    
    public int selectGenderAgeSmaller(String s,int age);
    
    public int selectAllSum();
    
    public List<String> getAllOnline();
    
    public List<String> getAllAppoffline();
    
    public int isemailexists(String email);
    public ArrayList<String> getUserName();

    public int insertImage();
    
     public int getUserId(String username);
     public User getUser(String mail);
     public int  setOffilne(int id ,String stat);
       public  String checkStatus(String name);


        
    
    
    
}
