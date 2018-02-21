/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import Daos.userDAO;
import RMI.RMI;
import chat.User;
import chat.request;
import chat.request_dao;
import chat.userDAO;
import chat.user_has_request;
import chat.user_has_request_dao;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.controlsfx.control.Notifications;
import userInterfaceUser.LoginPage;

/**
 * FXML Controller class
 *
 * @author Alaa
 */
public class ProfileController implements Initializable {

    @FXML
    private ImageView chat;
    @FXML
    private ImageView log_out;
    @FXML
    private ImageView profilePic;
    @FXML
    private Button change_image;
    @FXML
    private TextField edit_user;
    @FXML
    private TextField edit_email;
    @FXML
    private TextField edit_pass;
    @FXML
    private TextField edit_age;
    @FXML
    private Button edit_profile;
    @FXML
    private TextField text_search;
    @FXML
    private ImageView search_icon;
    @FXML
    private ListView list_view;

    private BufferedImage PPBuffered = null;
    private FileChooser fileChooser;
    private Image profilePic1;
    private String password = null;
    ArrayList<String> users;
    String mail;
    String pass;
    String uname;
    int age;
    User user = new User();
    User user_temp = new User();
    private RMI r;

    /**
     *
     * @param u
     * @param r
     */
    public ProfileController(User u, RMI r) {
        this.user = u;
        this.r = r;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listUsers();

        System.out.println(user.getId());

        change_image.setOnMousePressed((event) -> {

            fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Profile Image..");
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            fileChooser.getExtensionFilters().add(filter);

            File file = fileChooser.showOpenDialog(null);

            try {

                PPBuffered = ImageIO.read(file);
                profilePic1 = SwingFXUtils.toFXImage(PPBuffered, null);
                this.profilePic.setImage(profilePic1);
            } catch (IOException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        edit_profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if ((edit_user.getText().equals("")) || (edit_email.getText().equals("")) || (edit_pass.getText().equals("")) || (edit_age.getText().equals(""))) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Information");
                    alert.setHeaderText("Try Again");
                    alert.setContentText("please enter a valid information");
                    alert.showAndWait();
                } else {
                    while (password == null || password.trim().equalsIgnoreCase("")) {
                        password = JOptionPane.showInputDialog(null, "Please Enter You Old Password");
                    }

                    user.setEmail(edit_email.getText());
                    user.setPassword(password);
                    userDAO dao = new userDAO();
                    user_temp = dao.retrieve(user);
                    user_temp.setPassword(edit_pass.getText());
                    user_temp.setAge(Integer.parseInt(edit_age.getText()));
                    user_temp.setUsername(edit_user.getText());
                    int i = dao.update(user_temp);
                    if (i == -1 || i == -2) {
                        System.out.println("bazt");
                    } else {
                        System.out.println("done");
                    }
                }

            }
        });

        chat.setOnMousePressed((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\UI\\chat.fxml"));

                chatController c1 = new chatController(user, r);
                loader.setController(c1);

                LoginPage.getPrimaryStage().setScene(new Scene(loader.load(), 750, 650));
            } catch (IOException ex) {
                ex.printStackTrace();
                //  Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        logOut(); 

    }

    /**
     *Function that displays a list of users on the application in order to allow the user to add another user as a friend 
     */
    public void listUsers() {
        list_view.getItems().clear();
        userDAO d1 = new userDAO();
        users = new ArrayList();
        users = d1.getUserName();

        Platform.runLater(() -> {

            for (String user1 : users) {
                Label lbl = new Label();

                lbl.setText("Alaa Muhamed");

                lbl.setFont(Font.font("Cambria", 20));
                lbl.setTextFill(Color.web("#a77705"));

                Image imageDecline = new Image(getClass().getResourceAsStream("..\\resources\\req.png"), 25, 25, false, false);

                ImageView img = new ImageView(imageDecline);

                img.setOnMousePressed((event) -> {
                    String username = lbl.getText();
                    userDAO u1 = new userDAO();
                    int id = u1.getUserId(username);
                    request r1 = new request();
                    r1.setReciver_id(id);
                    r1.setStatus("pending");
                    request_dao r12 = new request_dao();
                    r12.create(r1);

                    int to = id;
                    int from = user.getId();
                    int count = r12.getReqcount();
                    System.out.println(count);
                    user_has_request re1 = new user_has_request(count, from, to);
                    user_has_request_dao dao1 = new user_has_request_dao();
                    dao1.create(re1);
                    JOptionPane.showMessageDialog(null, username + " " + "added");

                });

                HBox hbox = new HBox();

                hbox.setStyle("-fx-background-color: #331339;" + "-fx-padding: 15;" + "-fx-spacing: 10;");

                hbox.getChildren().addAll(lbl, img);

                //   lbl.setGraphic(new ImageView(path));
                lbl.setText((String) user1);
                list_view.getItems().add(hbox);
            }
        });

    }

    ///////////////////////////////////////////LOGOUT/////////////////////////////////////////////

    /**
     *Function for the user to logout and set his status to be offline.
     */
    public void logOut() {
        Platform.runLater(() -> {
            /// lma nn2l lazm nst5dmha fo2 w n check el query    
            log_out.setOnMousePressed((event) -> {
                Notifications not = Notifications.create()
                        .title("Ichat")
                        .text(user.getUsername() + "  " + "Is Offline ")
                        .graphic(null)
                        .hideAfter(Duration.seconds(10));
                not.darkStyle();
                not.showConfirm();

                userDAO d1 = new userDAO();
                d1.setOffilne(user.getId(), "offline");

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\UI\\login.fxml"));
                    LoginController l1 = new LoginController(r);
                    loader.setController(l1);

                    LoginPage.getPrimaryStage().setScene(new Scene(loader.load(), 400, 600));
                } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            );
        }
        );

    }

}
