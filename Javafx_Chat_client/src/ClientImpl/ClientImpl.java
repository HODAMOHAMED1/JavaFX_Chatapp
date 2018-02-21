/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientImpl;

import Interfaces.ClientInt;
import controllers.chatController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * 
 */

public class ClientImpl extends UnicastRemoteObject implements ClientInt {
    chatController controller;    

    /**
     *
     * @param controller
     */
    public void setController(chatController controller) {
        this.controller = controller;
    }
    
    /**
     *
     * @throws RemoteException
     */
    public ClientImpl() throws RemoteException {
    }

    /**
     *
     * @param msg
     * @param Sender
     * @param color
     * @param Size
     * @param fontFamily
     * @throws RemoteException
     */
    @Override
    public void receiveSender(String msg, String Sender, String color, int Size, String fontFamily) throws RemoteException {
        System.out.println(msg+ Sender+ color+ Size);
        controller.PrintSender(msg, Sender, color, Size, fontFamily);
    }

    /**
     *
     * @param msg
     * @param Sender
     * @param color
     * @param Size
     * @param fontFamily
     * @throws RemoteException
     */
    @Override
    public void receiveReciever(String msg, String Sender, String color, int Size, String fontFamily) throws RemoteException {
        controller.PrintReciever(msg, Sender, color, Size, fontFamily);
    }

    /**
     *
     * @param msg
     * @throws RemoteException
     */
    @Override
    public void recieveNotSer(String msg) throws RemoteException {
    Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notifications not = Notifications.create()
                .title("Ichat")
                .text(msg)
                .graphic(null)
                .hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.err.println("Notification Clicked !!!!");
            }
        });
        not.darkStyle();
        not.showConfirm();      
            }
        });
    }

}
