/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.MySQLConnection;


/**
 *
 * @author 3alilio
 */

public class dataBaseManager {
    private Connection con;

    public dataBaseManager() {
        try {
            DriverManager.registerDriver(new Driver());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
        } catch (SQLException ex) {
            //Logger.getLogger(dataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Connection Failed");
            ex.printStackTrace();
        }finally{System.out.println("Connected");}
    }

    public Connection getCon() {
        return con;
    }
    
   
    
    
    
    
    
    
    
}
