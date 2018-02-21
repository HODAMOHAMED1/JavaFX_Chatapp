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
import javax.swing.JOptionPane;

/**
 *
 * @author Freeware Sys
 */
public class FriendDAO implements FriendInt {

    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet results;
    private dataBaseManager dbm = new dataBaseManager();
    private Connection con = dbm.getCon();

    @Override
    public int addFriend(int to , int from) {
        try {
            PreparedStatement pst = con.prepareStatement("insert into chat.user_has_friend (user_id , friend_id) values (?,?)");
            pst.setInt(1,to);
            pst.setInt(2, from);
            if (pst.execute()) {
                return 1;
            }
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null, "Soryy can't add this user to this friend");
            ex.printStackTrace();
        } 
        return -1;
    }

//    @Override
//    public int removeFriend(Friend f1) {
//        try {
//            PreparedStatement pst = con.prepareStatement("delete from  chat.user_has_friend (user_id , friend_id) where  user_id=? and friend_id= ?");
//            pst.setInt(1, f1.getUserID());
//            pst.setInt(2, f1.getFriendID());
//            if (pst.execute()) {
//                return 1;
//            }
//        } catch (Exception ex) {
//            JOptionPane.showConfirmDialog(null, "Soryy can't delete this userfrom your friends");
//            ex.printStackTrace();
//        } finally {
//            try {
//                con.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(FriendDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return -1;
//        
//    }

    @Override
    public ArrayList<String> getFriends(int to) {
        
         ArrayList<String> friends = new ArrayList<>();
        try {
            stmt = con.createStatement();
           
                PreparedStatement pst = con.prepareStatement("SELECT username FROM chat.user WHERE user.id IN ( SELECT friend_id FROM chat.user_has_friend WHERE chat.user_has_friend.user_id = ? ) ");
             pst.setInt(1,to);
             results = pst.executeQuery();
            while (results.next()) {
                String user = results.getString("username");
                friends.add(user);

            }
            //  pst.setString(1, email);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return friends;
    }

    
    
    
    
    

}
