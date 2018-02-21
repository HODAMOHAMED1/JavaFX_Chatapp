/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import DTOs.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * 
 */
public interface ServerInt extends Remote{
         
    /**
     *
     * @param username
     * @throws RemoteException
     */
    void unregister(String username) throws RemoteException;
        
    /**
     *
     * @param username
     * @param clientRef
     * @throws RemoteException
     */
    void register(String username,ClientInt clientRef) throws RemoteException;
        
    /**
     *
     * @param msg
     * @param Users
     * @param Sender
     * @param color
     * @param size
     * @param fontFamily
     * @throws RemoteException
     */
    public void tellToOther(String msg, ArrayList<String> Users, String Sender, String color,int size, String fontFamily) throws RemoteException;
    
    /**
     *
     * @param email
     * @return
     * @throws RemoteException
     */
    int isemailexists(String email) throws RemoteException;
        
    /**
     *
     * @param username
     * @return
     * @throws RemoteException
     */
    List<User> getContactList(String username) throws RemoteException;
        
    /**
     *
     * @param email
     * @param passwword
     * @return
     * @throws RemoteException
     */
    User signin(String email,String passwword) throws RemoteException;
        
    /**
     *
     * @param msgfromusponsor
     * @throws RemoteException
     */
    void notifyOnline(String msgfromusponsor)throws RemoteException;  
}
