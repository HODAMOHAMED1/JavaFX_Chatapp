/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceUser;

import RMI.RMI;
import controllers.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 */
public class LoginPage extends Application{

    private static Stage primaryStage;

    /**
     *
     * @return
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    Parent root;
    private RMI r;
    @Override
    public void start(Stage primaryStage) throws Exception {
          
          FXMLLoader loader1 = new FXMLLoader(getClass().getResource("..\\UI\\login.fxml"));
          r = new RMI("192.168.1.8");
          LoginController logincontroller = new LoginController(r);
          loader1.setController(logincontroller);
          System.out.println("1");
          root = loader1.load();
          System.out.println("2");
          
          
          
          this.primaryStage=primaryStage;
          Scene login = new Scene(root);
          primaryStage.setScene(login);
          primaryStage.show();
          
          
 
 
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
