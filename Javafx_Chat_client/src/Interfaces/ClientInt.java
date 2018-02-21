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
 * 
 */
public interface ClientInt extends Remote {
    
    /**
     *
     * @param msg
     * @param Sender
     * @param color
     * @param Size
     * @param fontFamily
     * @throws RemoteException
     */
    void receiveSender(String msg, String Sender, String color,int Size, String fontFamily) throws RemoteException;
    
    /**
     *
     * @param msg
     * @param Sender
     * @param color
     * @param Size
     * @param fontFamily
     * @throws RemoteException
     */
    void receiveReciever(String msg, String Sender, String color,int Size, String fontFamily) throws RemoteException;
    
    /**
     *
     * @param msg
     * @throws RemoteException
     */
    void recieveNotSer(String msg) throws RemoteException;
}
