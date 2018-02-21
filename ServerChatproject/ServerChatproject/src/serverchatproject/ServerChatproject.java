/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverchatproject;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author 3alilio
 */
public class ServerChatproject {

   
    Registry reg;
    public static void main(String[] args) {
        // TODO code application logic here
      
    }
    
         public ServerChatproject(){
try{
        ServerImplementation obj = new ServerImplementation();
        reg = LocateRegistry.createRegistry(2500);
        reg.rebind("ichat", obj);
        System.out.println("binded suc.");
        
        
}
    catch(RemoteException ex){ 
        System.out.println("This is not an empty catch Server");
        Alert alert = new Alert(Alert.AlertType.ERROR);
                          alert.setTitle("Error");
                          alert.setContentText("Server Can't start");
                          alert.showAndWait();
    ex.printStackTrace();}
}
    public void close(){
        try {
            reg.unbind("ichat");
        } catch (RemoteException ex) {
            Logger.getLogger(ServerChatproject.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
                          alert.setTitle("Error");
                          alert.setContentText("Server Can't start");
                          alert.showAndWait();
        } catch (NotBoundException ex) {
            Logger.getLogger(ServerChatproject.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
                          alert.setTitle("Error");
                          alert.setContentText("Server Can't start");
                          alert.showAndWait();
        }
    }
}
