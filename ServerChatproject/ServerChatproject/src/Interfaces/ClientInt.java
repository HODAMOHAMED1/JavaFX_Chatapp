/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import javafx.scene.paint.Color;

/**
 *
 * @author 3alilio
 */
public interface ClientInt extends Remote {
    
  void receiveSender(String msg, String Sender, String color,int Size, String fontFamily) throws RemoteException;
    
    void receiveReciever(String msg, String Sender, String color,int Size, String fontFamily) throws RemoteException;
    
    void recieveNotSer(String msg) throws RemoteException;
}
