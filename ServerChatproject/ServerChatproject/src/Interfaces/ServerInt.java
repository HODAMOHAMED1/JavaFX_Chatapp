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
 * @author 3alilio
 */
public interface ServerInt extends Remote{
         
        void unregister(String username) throws RemoteException;
        
        void register(String username,ClientInt clientRef) throws RemoteException;
        
        public void tellToOther(String msg, ArrayList<String> Users, String Sender, String color,int size, String fontFamily) throws RemoteException;
    
        int isemailexists(String email) throws RemoteException;
        
        List<User> getContactList(String username) throws RemoteException;
        
        User signin(String email,String passwword) throws RemoteException;
        
        void notifyOnline(String msgfromusponsor)throws RemoteException;  
        
        public ClientInt getClientObj(String sender, String Reciever) throws RemoteException;
}
