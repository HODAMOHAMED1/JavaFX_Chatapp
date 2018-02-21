/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import chat.dataBaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alaa
 */
public class request_dao implements request_inter {

    private PreparedStatement pst;
    private Statement stmt;
    private ResultSet results;
    private dataBaseManager dbm = new dataBaseManager();
    private Connection con = dbm.getCon();

    @Override
    public int create(request element) {
        try {
             pst=con.prepareStatement("insert into chat.request (userid,status) values (?,?)");
          //  pst = con.prepareStatement("insert into chat.request  values (?,?)");
            pst.setInt(1, element.getReciver_id());
            pst.setString(2, element.getStatus());
            boolean i = pst.execute();
            if (i == false) {
                return -1;
            } else {
                return 1;
            }

        } catch (SQLException ex) {
            //    System.err.println("fih moshkella !!!!");
            //  Logger.getLogger(request_dao.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            return -1;

        }

    }

    @Override
    public int update(request element) {

        try {
          //  pst = con.prepareStatement("update chat.request set status=? where from=? And to = ?");
          pst = con.prepareStatement("update chat.request set status=? where reqid=? and userid= ?");
            pst.setInt(2, element.getReq_Id());
            pst.setInt(3, element.getReciver_id());
            pst.setString(1, element.getStatus());
            boolean i = pst.execute();
            if (i == false) {
                return -1;
            } else {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(request_dao.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    @Override
    public int delete(request parameter) {
        try {
            PreparedStatement pst = con.prepareStatement("delete from chat.request where status='ignored' ");
            ////////ignored aw confirmed 
            boolean i = pst.execute();
            if (i == true) {

                return -1;

            } else {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(request_dao.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    @Override
    public request retrieve(request element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public ArrayList<request> get(request element) {

        request r = new request();
        ArrayList R = new ArrayList();

        try {
            pst = con.prepareStatement("Select * from chat.request where userid = ?");
            pst.setInt(1, element.getReciver_id());
            results = pst.executeQuery();
            while (results.next()) {
                r.setReq_Id(results.getInt(1));
                r.setReciver_id(results.getInt(2));
                r.setStatus(results.getString(3));
                /////////arraylist//////////////////////

                R.add(r);
            }

            boolean i = pst.execute();
            if (i == false) {
                return R;
            } else {
                return R;
            }

        } catch (SQLException ex) {
            System.err.println(" error");

            Logger.getLogger(request_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return R;

    }

     @Override
    public int getReqcount() {
         int x =0 ;
         con = dbm.getCon();
        try {
            String query = "Select count(*) from chat.request";
            stmt = con.createStatement();
            results = stmt.executeQuery(query);
            while (results.next()) {
                    x = results.getInt(1);
                
            }
       
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return x;
    }


  
   
    
    

}
