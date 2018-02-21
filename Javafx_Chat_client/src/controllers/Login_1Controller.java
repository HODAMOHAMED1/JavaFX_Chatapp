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
//import User.userDAO ;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import userInterfaceUser.LoginPage;

/**
 * FXML Controller class
 *
 * 
 */
public class Login_1Controller implements Initializable {

    @FXML
    private Button register;

    @FXML
    private TextField user_name;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private TextField con_password;
    @FXML
    private ChoiceBox<String> country;
    @FXML
    private ChoiceBox<String> gender;

    ObservableList<String> options = FXCollections.observableArrayList("Egypt",
            "UK",
            "USA", "Germany", "Italy", "Japan", "Armenia", "France", "Seria", "China", "India"
    );

    ObservableList<String> gend = FXCollections.observableArrayList("Male", "Female");

    @FXML
    private TextField age;
    
        private RMI r;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * Register a new user and check user inputs.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = new User();
        country.setItems(options);
        gender.setItems(gend);

        register.setOnMousePressed((event) -> {
            if ((user_name.getText().equals("")) || (email.getText().equals("")) || (password.getText().equals("")) || (con_password.getText().equals("")) || (age.getText().equals(""))) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Information");
                alert.setHeaderText("Try Again");
                alert.setContentText("please enter a valid information");
                alert.showAndWait();

            } else {

                user.setUsername(user_name.getText());

                user.setEmail(email.getText());
                if (password.getText().equals(con_password.getText())) {

                    user.setPassword(password.getText());
                }

                user.setAge(Integer.parseInt((age.getText())));
                user.setCountry(country.getValue());
                user.setGender(gender.getValue());
                userDAO test = new userDAO();
                int i = test.create(user);
                if (i == -1) {
                    System.out.println("sha8alaa");
                     try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\UI\\login.fxml"));

                    //  loader.setController(new LoginController());
                    LoginController l1 = new LoginController(r);
                    loader.setController(l1);

                    LoginPage.getPrimaryStage().setScene(new Scene(loader.load(), 400, 600));
                } catch (IOException ex) {
                    Logger.getLogger(Login_1Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    
                    
                    
                } else {
                    System.out.println("done");
                }
            }
        }
        );
    }

    // TODO
}
