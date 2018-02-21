/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceUser;

import controllers.EntryController;
import controllers.LoginController;
import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 
 * @author 3alilio
 */
public class EnterIP extends Application{

    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception {
          
          FXMLLoader loader1 = new FXMLLoader(new File("src\\UI\\Entry.fxml").toURL());
          System.out.println(getClass().getResource("..\\UI\\Entry.fxml"));
//          EntryController cont = new EntryController();
//          loader1.setController(cont);
          System.out.println("1");
       //  root = FXMLLoader.load(getClass().getResource("..\\UI\\login.fxml"));
          root = loader1.load();
          System.out.println("2");
          
          this.primaryStage=primaryStage;
          Scene login = new Scene(root);
          primaryStage.setScene(login);
          primaryStage.show();
          
 
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
