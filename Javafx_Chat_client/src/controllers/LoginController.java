/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import RMI.RMI;
import chat.User;
import chat.userDAO;
import java.io.IOException;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import userInterfaceUser.LoginPage;

/**
 *
 * 
 */
public class LoginController implements Initializable {

    @FXML
    Button loginBtn;

    @FXML
    TextField emailInput;

    @FXML
    PasswordField passInput;

    @FXML
    Text signupText;

    Pattern ptn = Pattern.compile("^(\\w*)\\@(\\w*)\\.com");
    userDAO d1 = new userDAO();
         String mail ;
         String password ; 
         User user ;
       
    private RMI r;

    /**
     *
     * @param r
     */
    public LoginController(RMI r) {
        this.r = r;
    }
    
    
         
         
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signupText.setOnMousePressed((ev) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\UI\\signups.fxml"));
                loader.setController(new Login_1Controller());
                
                LoginPage.getPrimaryStage().setScene(new Scene(loader.load(), 400, 600));
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
      loginBtn.setOnMousePressed((event) -> {

        checkSignin();
            
        });
          
    }
               
    /**
     *Function to authenticate the user.
     */
    @FXML
    public void checkSignin() {
        Matcher mtch = ptn.matcher(emailInput.getText().trim());
        mail=emailInput.getText();
        password = passInput.getText();
        user = new User(mail, password);
        user = d1.getUser(mail);
        System.out.println(user.getEmail() + "   "+ user.getPassword());
        if (emailInput.getText().trim().length() == 0 || passInput.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please Enter both email and password", "Error 404", JOptionPane.WARNING_MESSAGE);
        } else if (!mtch.find()) {
            JOptionPane.showMessageDialog(null, "Email is not right pleasecheck", "Error 404", JOptionPane.WARNING_MESSAGE);

        } else if(user.getPassword().equals(password)&&mail.equalsIgnoreCase(mail)){
           try {
               d1.setOffilne(user.getId(),"online");
                   Notifications not = Notifications.create()
                        .title("Ichat")
                        .text(user.getUsername() + "  " + "Is Online ")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10));
                not.darkStyle();
                not.showConfirm();
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\UI\\chat.fxml"));
                    System.out.println("User from Login Controller" + user.getUsername());
                    chatController p1 = new chatController(user,r);
                    
                    loader.setController(p1);
                
                LoginPage.getPrimaryStage().setScene(new Scene(loader.load(), 750, 450));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("mesh shaghal");
            }
           
        }
    }

    private boolean ispasswordright(String email, String password) {
        return true;
    }

    /**
     *
     * @return user ID knowing his email address.
     */
    public int getId ()
    {
        String user = emailInput.getText();
     
        return d1.getUsersId(user);
    }
    
    //////////////////////////////////////////////////////////////////////
  
  
    
}
