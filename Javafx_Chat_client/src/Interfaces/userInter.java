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
 * 
 * @author Freeware Sys
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

    /**
     *
     * @return
     */
    public ResultSet selectAll();
    
    /**
     *
     * @return
     */
    public int selectFemales();
    
    /**
     *
     * @return
     */
    public int selectMales();

    /**
     *
     * @return
     */
    public int selectOnline();
    
    /**
     *
     * @return
     */
    public int selectOffline();
    
    /**
     *
     * @return
     */
    public int selectBusy();
    
    /**
     *
     * @return
     */
    public int selectAway();
    
    /**
     *
     * @return
     */
    public int selectAvaible();

    /**
     *
     * @param s
     * @param age
     * @return
     */
    public int selectGenderAgeGreater(String s,int age);
    
    /**
     *
     * @param s
     * @param age
     * @return
     */
    public int selectGenderAgeSmaller(String s,int age);
    
    /**
     *
     * @return
     */
    public int selectAllSum();
    
    /**
     *
     * @return
     */
    public List<String> getAllOnline();
    
    /**
     *
     * @return
     */
    public List<String> getAllAppoffline();
    
    /**
     *
     * @param email
     * @return
     */
    public int isemailexists(String email);

    /**
     *
     * @return
     */
    public ArrayList<String> getUserName();

    /**
     *
     * @return
     */
    public int insertImage();
    
    /**
     *
     * @param username
     * @return
     */
    public int getUserId(String username);

    /**
     *
     * @param mail
     * @return
     */
    public User getUser(String mail);

    /**
     *
     * @param id
     * @param stat
     * @return
     */
    public int  setOffilne(int id ,String stat);

    /**
     *
     * @param name
     * @return
     */
    public  String checkStatus(String name);


        
    
    
    
}
