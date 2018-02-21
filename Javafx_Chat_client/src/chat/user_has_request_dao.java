/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

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
public class user_has_request_dao implements user_has_request_inter {

    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet results;
    private dataBaseManager dbm = new dataBaseManager();
    private Connection con;

    @Override
    public int delete(user_has_request element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public user_has_request retrieve(user_has_request element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(user_has_request element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(user_has_request element) {
        try {
            con = dbm.getCon();
            pst = con.prepareStatement("insert into chat.user_has_request (sender,receiver,reqid) values (?,?,?)");
            pst.setInt(1, element.getFrom());
            pst.setInt(2, element.getTo());
            pst.setInt(3, element.getReq_id());
            boolean i = pst.execute();
            if (i == false) {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_has_request_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    @Override
    public ArrayList<Integer> getId(int receiver) {
        ArrayList<Integer> Ids = new ArrayList<>();
        con = dbm.getCon();
        try {

            pst = con.prepareStatement("SELECT chat.user_has_request.sender FROM chat.user_has_request INNER JOIN chat.request ON chat.request.reqid = chat.user_has_request.reqid AND receiver = ?");
            pst.setInt(1, receiver);
           results =  pst.executeQuery();
            while (results.next()) {
                Ids.add(results.getInt(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(user_has_request_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ids;
    }

    @Override
    public ArrayList getUsers(int receiver) {
      ArrayList<String> Ids = new ArrayList<>();
        con = dbm.getCon();
        try {

          //  pst = con.prepareStatement("SELECT username FROM chat.user INNER JOIN chat.user_has_request ON user.id = chat.user_has_request.sender and chat.user_has_request.receiver = ? ");
          pst =con.prepareStatement("SELECT username FROM chat.user INNER JOIN chat.user_has_request ON user.id = chat.user_has_request.sender INNER JOIN chat.request ON chat.request.reqid = chat.user_has_request.reqid and chat.user_has_request.receiver = ? AND chat.request.status = ? ");
          pst.setInt(1, receiver);
          pst.setString(2 , "pending");
          
          results =  pst.executeQuery();
            while (results.next()) {
                Ids.add(results.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_has_request_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ids;
        
        
        
    }

    @Override
    public void updateStatus(int from, int to, String stat) {
         con = dbm.getCon();
        try {
           
            pst = con.prepareStatement("UPDATE chat.request SET status = ? WHERE request.reqid =( SELECT chat.user_has_request.reqid FROM chat.user_has_request WHERE chat.user_has_request.sender = ? AND chat.user_has_request.receiver = ? )");
            pst.setString(1,stat);
            pst.setInt(2,from );
            pst.setInt(3,to);
            boolean i = pst.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(user_has_request_dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
