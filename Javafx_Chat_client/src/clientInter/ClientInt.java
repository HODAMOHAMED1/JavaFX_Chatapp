/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientInter;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Alaa
 */
public interface ClientInt extends Remote  {

//void receiveSender(String msg ,String sender ) throws RemoteException;
    void receiveReciever(String msg ,String reciever) throws RemoteException;
    void receiveSender(String msg, String sender) throws RemoteException ;
    boolean isOnline()throws RemoteException;    
}
