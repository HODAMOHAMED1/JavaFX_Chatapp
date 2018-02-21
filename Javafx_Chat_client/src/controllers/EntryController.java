/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author 3alilio
 */
 

import RMI.RMI;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;





public class EntryController implements Initializable{
    
 @FXML
 private TextField enterIP;
 
 @FXML
 private Button subIP;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
       @FXML
       private void checkip() throws IOException{
        
        
        String IP = enterIP.getText().trim();
        
        Pattern ptn = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
					"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        Matcher mtch = ptn.matcher(IP);
        
        if(!mtch.find())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("IP Address is not valid");
            alert.showAndWait();
            enterIP.setText("");
        }
        else
        {
            try {
                RMI con = new RMI(enterIP.getText());
                
                Stage primaryStage = (Stage) enterIP.getScene().getWindow();
       
                
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("..\\UI\\login.fxml"));


                System.out.println("1");
                Parent root = loader1.load();
                System.out.println("2");

                Scene login = new Scene(root);
                primaryStage.setScene(login);
                primaryStage.show();
                
                //go to sign in page and pass the value of the ip to run it.
            } catch (MalformedURLException ex) {
                Logger.getLogger(EntryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
