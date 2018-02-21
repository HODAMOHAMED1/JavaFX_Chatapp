/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import ClientImpl.ClientImpl;
import Interfaces.ServerInt;
import java.net.InetAddress;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 *R
 *
 */
public class RMI {

    ServerInt Ref;

    /**
     *
     * @return
     */
    public ServerInt getRef() {
        return Ref;
    }

    /**
     *
     * @param IP get the IP from the server which is the host IP
     */
    public RMI(String IP) {

        try {
            Registry reg = LocateRegistry.getRegistry(IP, 2500);
            Ref = (ServerInt) reg.lookup("ichat");
//            ClientImpl cl = new ClientImpl();
//            Ref.register("Shawarby", cl);
            System.out.println("Connected");
        } catch (RemoteException ex) {
            System.out.println("Remote exception");
            Logger.getLogger(RMI.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Server is Down");
                alert.setContentText("please try again later");
                alert.showAndWait();
                }                
            });
        } catch (NotBoundException ex) {
            System.out.println("NotBoundException exception");
            Logger.getLogger(RMI.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            Platform.runLater(new Runnable() {                
                @Override
                public void run() {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Server is Down");
                alert.setContentText("please try again later");
                alert.showAndWait();
                }                
            });
        }
    }

}
