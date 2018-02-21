/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import DTOs.User;
import Interfaces.userInter;
import dataBaseManager.dataBaseManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 3alilio
 */
public class userDAO implements userInter {

    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet results;
    private dataBaseManager dbm = new dataBaseManager();
    private Connection con;
    int nomale, nofemals , totalusers ,online , offline , away , busy , plus18,minus18;
    ArrayList user = new ArrayList();

    @Override
    public int create(User element) {
        con = dbm.getCon();
        try {
            PreparedStatement pst = con.prepareStatement("insert into chat.user (email,password,gender,country,phoneNum,username) values (?,?,?,?,?,?)");
            pst.setString(1, element.getEmail());
            pst.setString(2, element.getPassword());
            pst.setString(3, element.getGender());
            pst.setString(4, element.getCountry());
            pst.setString(5, element.getPhoneNum());
            pst.setString(6, element.getUsername());
            boolean i = pst.execute();
            if (i == false) {
                con.close();
                return -1;
            } else {
                con.close();
                return 1;
            }
        } catch (SQLException ex) {
            // Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            //System.err.println("error");
            ex.printStackTrace();
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return -2;
        }
    }

    @Override
    public int update(User element) {
        con = dbm.getCon();
        try {

            PreparedStatement pst = con.prepareStatement("update chat.user set password=?,username=?,gender=?,country=?,phoneNum=? where email=?");
            pst.setString(6, element.getEmail());
            pst.setString(1, element.getPassword());
            pst.setString(3, element.getGender());
            pst.setString(4, element.getCountry());
            pst.setString(5, element.getPhoneNum());
            pst.setString(2, element.getUsername());
            boolean i = pst.execute();
            if (i == false) {
                con.close();
                return -1;
            } else {
                con.close();
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return -2;
        }
    }

    /**
     *
     * @param parameter
     * @return
     */
    @Override
    public int delete(User parameter) {
        con = dbm.getCon();
        try {
            PreparedStatement pst = con.prepareStatement("delete from chat.user where email=?");
            pst.setString(1, parameter.getEmail());
            boolean i = pst.execute();
            if (i == true) {
                con.close();
                return -1;

            } else {
                con.close();
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return -2;
        }
    }

    @Override
    public User retrieve(User element) {
        con = dbm.getCon();
        User Utemp = new User();
        try {
            PreparedStatement pst = con.prepareStatement("Select username,email,password,gender,status,country from chat.user where email = ?");
            pst.setString(1, element.getEmail());
           // pst.setString(2, element.getPassword());
            boolean i = pst.execute();
            if (i == true) {
                results = pst.executeQuery();
                results.next();
                Utemp.setUsername(results.getString(1));
                Utemp.setEmail(results.getString(2));
                Utemp.setPassword(results.getString(3));
                Utemp.setGender(results.getString(4));
                Utemp.setStatus(results.getString(5));
                Utemp.setCountry(results.getString(6));
                System.out.println(Utemp.getEmail()+ "  "+ Utemp.getUsername());
                con.close();
                return Utemp;
            } else {
                Utemp.setId(-1);
                return Utemp;
            }
        } catch (SQLException ex) {

            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Utemp.setId(-2);
            return Utemp;
        }
    }

    @Override
    public int selectAllSum() {
        con = dbm.getCon();
        try {
            String query = "Select count(*) from chat.user";
            stmt = con.createStatement();
            results = stmt.executeQuery(query);
            while (results.next()) {
                totalusers = results.getInt(1);
            }
          //  con.close();
            return totalusers;
                    //results.getType();
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return -2;
        }
    }

    @Override
    public int selectFemales() {
        try {
            con = dbm.getCon();
            String query = "Select count(*) from chat.user where gender = 'Female'";
            stmt = con.createStatement();
            results = stmt.executeQuery(query);
            while (results.next()) {
                nofemals = results.getInt(1);
            }
           // con.close();
            return nofemals;
            //results.getType();
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
        }
    }

    @Override
    public int selectMales() {
        try {
            con = dbm.getCon();
            String query = "Select count(*) from chat.user where gender = 'Male'";
            stmt = con.createStatement();
            results = stmt.executeQuery(query);
            while (results.next()) {
                nomale = results.getInt(1);
            }
            // con.close();
            return nomale;
            //results.getType();
        } catch (SQLException ex) {
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }

            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
        }
    }

    @Override
    public int selectOnline() {
        try {
            con = dbm.getCon();
            String query = "Select count(*) from chat.user where status = 'online'";
            stmt = con.createStatement();
            results = stmt.executeQuery(query);
            while (results.next()) {
                online = results.getInt(1);
            }
          //  con.close();
            return online;
                    //results.getType();
        } catch (SQLException ex) {
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
        }
    }

    @Override
    public int selectOffline() {
        try {
            con = dbm.getCon();
            String query = "Select count(*) from chat.user where status = 'offline'";
            stmt = con.createStatement();
            results = stmt.executeQuery(query);
            while (results.next()) {
                offline = results.getInt(1);
            }
           // con.close();
            return offline;
                    //results.getType();
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }

            return -2;
        }
    }

    @Override
    public int selectBusy() {
        try {
            con = dbm.getCon();
            String query = "Select count(*) from chat.user where status = 'busy'";
            stmt = con.createStatement();
            results = stmt.executeQuery(query);
            while (results.next()) {
                busy = results.getInt(1);
            }
           // con.close();
            return busy;
                    //results.getType();
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }

            return -2;
        }
    }

    @Override
    public int selectAway() {
        try {
            con = dbm.getCon();
            String query = "Select count(*) from chat.user where status = 'away'";
            stmt = con.createStatement();
            results = stmt.executeQuery(query);
          //  con.close();
          while (results.next()) {
                away = results.getInt(1);
            }
            return away;
                    //results.getType();
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }

            return -2;
        }
    }

    @Override
    public int selectAvaible() {
        try {
            con = dbm.getCon();
            String query = "Select count(*) from users where status = 'Avaible'";
            stmt = con.createStatement();
            results = stmt.executeQuery(query);
            con.close();
            return results.getType();
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }

            return -2;
        }
    }

    @Override
    public int selectGenderAgeGreater(String gender, int age) {
        con = dbm.getCon();
        try {
            PreparedStatement pst = con.prepareStatement("select * from chat.user where gender = ? AND age > ?");
            pst.setString(1, gender);
            pst.setInt(2, age);
            boolean i = pst.execute();
            if (i == true) {
                results = pst.executeQuery();
                while (results.next()) {
                plus18 = results.getInt(1);
            }
                return plus18;
                        //results.getType();
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -2;
    }

    @Override
    public int selectGenderAgeSmaller(String gender, int age) {
        con = dbm.getCon();
        try {
            PreparedStatement pst = con.prepareStatement("select * from chat.user where gender = ? AND age < ?");
            pst.setString(1, gender);
            pst.setInt(2, age);
            boolean i = pst.execute();
            if (i == true) {
                results = pst.executeQuery();
                 while (results.next()) {
                minus18 = results.getInt(1);
            }
                return minus18;
                       // results.getType();
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -2;
    }

    @Override
    public ResultSet selectAll() {
        try {
            con = dbm.getCon();
            stmt = con.createStatement();
            results = stmt.executeQuery("select * from chat.users");
            con.close();
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return results;
        }
    }

    @Override
    public List<String> getAllOnline() {
        con = dbm.getCon();
        ArrayList<String> users_online = new ArrayList<>();
        try {
            stmt = con.createStatement();
            results = stmt.executeQuery("select id from chat.users where isonline = '1'");
            while (results.next()) {
                users_online.add((results.getString(1)));
            }
            con.close();

            return users_online;
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return users_online;
        }

    }

    @Override
    public List<String> getAllAppoffline() {
        con = dbm.getCon();
        ArrayList<String> users_online = new ArrayList<>();
        try {
            stmt = con.createStatement();
            results = stmt.executeQuery("select id from chat.users where isappOffline = '1' and isonline = '1'");
            
            while (results.next()) {
                users_online.add((results.getString(1)));
            }
            con.close();

            return users_online;
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return users_online;
        }
    }

    @Override
    public int isemailexists(String email) {
        con = dbm.getCon();
        try {
            PreparedStatement pst = con.prepareStatement("select email from chat.user where email= ?");
            pst.setString(1, email);
            boolean i = pst.execute();
            if (i == true) {
                results = pst.executeQuery();

                if (results.getString(1).equalsIgnoreCase(email)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -2;
    }
    public ArrayList retrieveServer(String email) {
        con = dbm.getCon();
        User Utemp = new User();
        user = new ArrayList<>();
        try {
            PreparedStatement pst = con.prepareStatement("Select username,id,country,gender,status from chat.user where email = ?");
            pst.setString(1, email);
           // pst.setString(2, element.getPassword());
            boolean i = pst.execute();
            if (i == true) {
                results = pst.executeQuery();
                while ( results.next()) {
                    String maString = results.getString(1);
                    user.add(maString);
                    int id = results.getInt(2);
                    user.add(id);
                     String country = results.getString(3);
                    user.add(country);
                     String gender = results.getString(4);
                     user.add(gender);
                     String status = results.getString(5);
                     user.add(status);           
                }
                return user;
            } else {
                con.close();
                Utemp.setId(-1);
                return user;
            }
        } catch (SQLException ex) {

            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Utemp.setId(-2);
            return user;
        }
    }
@Override
    public ArrayList<String> getUserName() {
        ArrayList<String> users = new ArrayList<>();
        try {
            con = dbm.getCon();
            stmt = con.createStatement();
            results = stmt.executeQuery("Select username from chat.user");
//            PreparedStatement pst = con.prepareStatement("Select username from chat.user");
//           stmt = con.createStatement();
            //  results = stmt.executeQuery();
            while (results.next()) {
                String user = results.getString("username");
                users.add(user);

            }
            //  pst.setString(1, email);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public int insertImage() {
        FileInputStream inputStream = null;

        try {
            File image = new File("F:\\graduation fair pictures\\_-57.jpg");
            inputStream = new FileInputStream(image);
            con = dbm.getCon();
            stmt = con.createStatement();
            PreparedStatement pst = con.prepareStatement("update chat.user  set pp = ? where email = ?");

            pst.setBinaryStream(1, (InputStream) inputStream, (int) (image.length()));
            pst.setString(2, "a@a.com");

            pst.executeUpdate();

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: - " + e);
        } catch (SQLException e) {
            System.out.println("SQLException: - " + e);
            return -1;
        } finally {

            return 0;

        }

    }

    @Override
    public int getUserId(String username) {
        int ids = 0;
        con = dbm.getCon();
        try {
            stmt = con.createStatement();
            PreparedStatement pst = con.prepareStatement("Select id  from chat.user where username = ? ;");
            pst.setString(1, username);
            results = pst.executeQuery();
            while (results.next()) {
                ids = results.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ids;

    }

    public int getUsersId(String mail) {
        int ids = 0;
        con = dbm.getCon();
        try {
            stmt = con.createStatement();
            PreparedStatement pst = con.prepareStatement("Select id  from chat.user where email = ? ;");
            pst.setString(1, mail);
            results = pst.executeQuery();
            while (results.next()) {
                ids = results.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ids;

    }

    @Override
    public User getUser(String mail) {
        
     User tmp = new User();
          con = dbm.getCon();
          try{
           PreparedStatement pst = con.prepareStatement("Select * from chat.user where email = ? ");
           pst.setString(1, mail);
            results = pst.executeQuery();
            while (results.next()) {
                tmp.setId(results.getInt(1));
                tmp.setUsername(results.getString(2));
                tmp.setPassword(results.getString(3));
                tmp.setEmail(results.getString(4));
               tmp.setGender(results.getString(5));
               tmp.setCountry(results.getString(6));
               tmp.setPhoneNum(results.getString(7));
               tmp.setAge(results.getInt(8));
               tmp.setStatus(results.getString(9));
              tmp.setIsonline(results.getString(11));
              tmp.setIsappoffline(results.getString(12));
              System.out.println(tmp.getEmail());
            }
            }catch (Exception e){System.err.println(" il catch msh fadya ");e.printStackTrace();}
   
        return tmp;
          }
//////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
     public int  setOffilne(int id , String stat){
        
         con = dbm.getCon();
        try {
           
            pst = con.prepareStatement("UPDATE chat.user SET status = ? WHERE chat.user.id = ? ");
            pst.setString(1,stat);
            pst.setInt(2,id );
           
            
            boolean i = pst.execute();
        if (i == false) {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
        
    }
////////////////////////////////////////////////////////////////////////////
    @Override
    public String checkStatus(String name) {
     con = dbm.getCon();
     String user =null;
        try {
           
            pst = con.prepareStatement("select status from chat.user where username = ?");
            pst.setString(1,name);
             results = pst.executeQuery();
        while(results.next()) {
              user = results.getString("status");
             
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(userDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  user ;
    }

    public ArrayList<String> retrieveEmails() {
        ArrayList<String> users = new ArrayList<>();
        try {
            con = dbm.getCon();
            stmt = con.createStatement();
            results = stmt.executeQuery("Select email from chat.user");
            while (results.next()) {
                String user = results.getString("email");
                users.add(user);
            }
            return users;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
