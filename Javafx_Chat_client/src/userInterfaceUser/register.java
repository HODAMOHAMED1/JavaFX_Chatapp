/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterfaceUser;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controllers.Login_1Controller;

/**
 *
 * 
 */
public class register extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root; 
        try {
            System.out.println(getClass());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\UI\\signups.fxml"));
            System.err.println(loader);
            loader.setController(new Login_1Controller());
            root = loader.load();
            Scene scene = new Scene(root, 400, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
