/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Freeware Sys
 */
public class DaoMain {
   static ArrayList<String> emArrayList = new ArrayList();
    
    public static void main(String[] args) {
        userDAO d1 = new userDAO();
//       int x = d1.selectGenderAgeSmaller("Female", 18);
//        System.out.println(x);
       emArrayList =  d1.retrieveServer("yalla@yalla.com");
       for(Object x2 : emArrayList)
       {
         System.err.println(x2);
       }
       
    }
    
}
