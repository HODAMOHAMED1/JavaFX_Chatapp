/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author Freeware Sys
 */
public class Friend {

    int friendID;
    int userID;

    /**
     *
     * @return
     */
    public int getFriendID() {
        return friendID;
    }

    /**
     *
     * @param friendID
     */
    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }

    /**
     *
     * @return
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }
}
