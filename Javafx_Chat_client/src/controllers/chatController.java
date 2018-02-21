/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import ClientImpl.ClientImpl;
import chat.User;
import Interfaces.ServerInt;
import RMI.RMI;
import RMI.msg;
import XML.SaveXML;
import chat.FriendDAO;
import chat.request;
import chat.request_dao;
import chat.userDAO;
import chat.user_has_request;
import chat.user_has_request_dao;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import userInterfaceUser.LoginPage;

/**
 * FXML Controller class
 *
 * @author hoda.CO
 */
public class chatController extends Thread implements Initializable {

    private String lastSender;

    @FXML
    private VBox vbox;
    @FXML
    private ListView ListFriends;
    @FXML
    private ListView ListRequest;
    @FXML
    private ListView group;

    @FXML
    private TextArea text_area;
    @FXML
    private TextField tex_f;
    @FXML
    private Button send;
    @FXML
    private Label uu;
    @FXML
    private Circle c;

    @FXML
    private ChoiceBox status;
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;

    @FXML
    private TabPane chattabpane;

    @FXML
    private Tab chattab;

    @FXML
    private VBox vboxchat;

    @FXML
    private Tab t1;
    @FXML
    private Tab t2;

    @FXML
    private Tab Group;

    @FXML
    private TabPane t3;

    @FXML
    private ComboBox<String> ss;
    ///////////////////////////////////Shawarbyyyyy/////////////////////////////////////////// 
    @FXML
    private ComboBox<Integer> comboBoxSize;

    @FXML
    private ComboBox<String> comboboxfont;

    @FXML
    private ComboBox<Color> comboBoxColor;

    @FXML
    private Button saveChat;

    private ArrayList<String> names = new ArrayList<>();

    private HashMap<String, Tab> tabnames = new HashMap<>();

    private HashMap<Tab, String> tablastsender = new HashMap<>();

    private HashMap<Color, String> Colorstext = new HashMap<>();

    private HashMap<String, Color> reverseColorstext = new HashMap<>();

    private Color textColor = Color.GREEN;

    private int size = 10;
    private String fontfamily = "Fleftex";
    private HashMap<String, ArrayList<msg>> xmltab = new HashMap<>();

// private ArrayList<String> names = new ArrayList<>();
/////////////////////////////////////////////////////////////////////////////////////////
    private ArrayList<String> users;
    private ArrayList<String> groups;

    private User user = new User();

    private String Stat = null;

    /**
     * Initializes the controller class.
     */
    public String msg;
    private RMI r;
    private ServerInt Ref;
    private SaveXML save;

    ObservableList<String> options = FXCollections.observableArrayList("online", "offline", "busy", "away");
    ObservableList<Integer> fontSizes = FXCollections.observableArrayList(10, 14, 18, 22, 28);

    ObservableList<String> fontNames = FXCollections.observableArrayList("Fleftex", "Courier New", "Arial", "Times New Roman");
    ClientImpl cl;

    private msg message;

    /**
     *
     * @return
     */
    public msg getMessage() {
        return message;
    }

    Registry reg;

    /**
     *
     * @param Ref
     */
    public void setRef(ServerInt Ref) {
        this.Ref = Ref;
    }
////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param u
     * @param r
     */
    public chatController(User u, RMI r) {
        this.user = u;
        this.r = r;
        Ref = r.getRef();
        try {
            cl = new ClientImpl();
            cl.setController(this);
            System.out.println(u.getUsername());
            Ref.register(u.getUsername(), cl);
        } catch (RemoteException ex) {
            Logger.getLogger(chatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ///////////////////////////////////////////////////INITIALIZE/////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uu.setText(user.getUsername());

        chattabpane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                upatenamelist();
                System.out.println(tablastsender.get(chattabpane.getSelectionModel().getSelectedItem()));
            }
        }
        );

        listFriends();
        listUsers();
        groupFriends();
        
        t2.setOnSelectionChanged((event) -> {
            listUsers();
        });
        t1.setOnSelectionChanged((event) -> {
            listFriends();
        });

        Group.setOnSelectionChanged((event) -> {
            groupFriends();
        });

        image1.setOnMousePressed((event) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("..\\UI\\profile.fxml"));

            ProfileController p1 = new ProfileController(user, r);
            loader.setController(p1);

            try {
                LoginPage.getPrimaryStage().setScene(new Scene(loader.load(), 750, 650));
            } catch (IOException ex) {
                Logger.getLogger(chatController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        chattabpane.tabClosingPolicyProperty().set(TabPane.TabClosingPolicy.ALL_TABS);

//---------------------------------------------------------------------------------------------------------        
        Color myColor = Color.BLACK;

        comboBoxColor.getItems().addAll(Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, myColor, Color.PURPLE);
        Colorstext.put(Color.RED, "red");
        Colorstext.put(Color.YELLOW, "yellow");
        Colorstext.put(Color.GREEN, "green");
        Colorstext.put(Color.BLUE, "blue");
        Colorstext.put(Color.BLACK, "black");
        Colorstext.put(Color.PURPLE, "purple");

        reverseColorstext.put("red", Color.RED);
        reverseColorstext.put("yellow", Color.YELLOW);
        reverseColorstext.put("green", Color.GREEN);
        reverseColorstext.put("blue", Color.BLUE);
        reverseColorstext.put("black", Color.BLACK);
        reverseColorstext.put("purple", Color.PURPLE);
        comboBoxColor.setCellFactory(new colorCellFactory());

        comboBoxColor.setButtonCell(comboBoxColor.getCellFactory().call(null));
        comboBoxColor.getSelectionModel().select(2);

        //  t1.setContent(listview);--------------------------------- de kman mfrod mlhash lazma
//          ListFriends.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                
//            }
//        }
//        );
//
//        DTOs.User user = new DTOs.User();
//--------------------------------------------------------a da brdo ------------------------------------------         
//         for (String name : groups) {
//               
//             
//              System.out.println(name);
//              
//        }
//        
//        }
        System.out.println(user.getId());
        ss.setItems(options);
        comboBoxSize.setItems(fontSizes);
        comboboxfont.setItems(fontNames);

    }

////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @return
     */
    public String getUserName() {
        String userName = uu.getText();
        return userName;
    }

///////////////////////////////////////////Online status////////////////////////////////////////////////////////////
      /**
     * Function that takes no parameters and updates the user status/
     *
     *
     */
    @FXML
    public void mm() {
        userDAO u1 = new userDAO();

        String st = ss.getSelectionModel().getSelectedItem().toString();
        switch (st) {

            ///////////////////////////// Alaa 3dlt 7agat ////////////////////////////////////
            case "offline":
                c.setFill(Color.GRAY);
                u1.setOffilne(user.getId(), "offline");
                break;
            case "online":
                c.setFill(Color.GREEN);
                u1.setOffilne(user.getId(), "online");
                break;
            case "busy":
                c.setFill(Color.RED);
                u1.setOffilne(user.getId(), "busy");

                break;
            case "away":
                c.setFill(Color.RED);
                u1.setOffilne(user.getId(), "away");

                break;
        }
//});

    }
///////////////////////////////////////////REQUESTS/////////////////////////////////////////////////////////////////////

    /**
     *Function that takes no parameters and displays the  requests from other users and let user to accept or reject the request ,
     * If he accepts , The user will be added to his contact list.
     */
    public void listUsers() {
        ListRequest.getItems().clear();
        user_has_request_dao d1 = new user_has_request_dao();
        users = new ArrayList();
        users = d1.getUsers(user.getId());
        Platform.runLater(new Runnable() {
            public void run() {

                for (String user1 : users) {
                    Label lbl = new Label();

                    lbl.setText(user1);

                    lbl.setFont(Font.font("Cambria", 20));
                    lbl.setTextFill(Color.web("#a77705"));

                    Image imageDecline = new Image(getClass().getResourceAsStream("..\\resources\\Checkmark.png"), 25, 25, false, false);
                    ImageView img = new ImageView(imageDecline);

                    Image imageAccept = new Image(getClass().getResourceAsStream("..\\resources\\wrong.2png.png"), 25, 25, false, false);
                    ImageView img1 = new ImageView(imageAccept);

                    img.setOnMousePressed((event) -> {

                        String username = lbl.getText();
                        userDAO u1 = new userDAO();
                        int from = u1.getUserId(username);
                        int to = user.getId();
                        user_has_request_dao d = new user_has_request_dao();
                        d.updateStatus(from, to, "approved");
                        JOptionPane.showMessageDialog(null, username + " "+"is Accepted");
                        FriendDAO d1 = new FriendDAO();
                        d1.addFriend(to, from);
                        d1.addFriend(from, to);
                    });

                    img1.setOnMousePressed((event) -> {

                        String username = lbl.getText();
                        userDAO u1 = new userDAO();
                        int from = u1.getUserId(username);
                        int to = user.getId();
                        user_has_request_dao d = new user_has_request_dao();
                        d.updateStatus(from, to, "Rejected");
                       JOptionPane.showMessageDialog(null, username + " "+"is Ignored");
                    });

                    HBox hbox = new HBox();

                    hbox.setStyle("-fx-background-color: #331339;" + "-fx-padding: 15;" + "-fx-spacing: 10;" + "-fx-background-radius: 20px;");

                    hbox.getChildren().addAll(lbl, img, img1);

                    //   lbl.setGraphic(new ImageView(path));
                    lbl.setText((String) user1);
                    ListRequest.getItems().add(hbox);
                }
            }
        });
//
    }

/////////////////////////////////////////////////////FRIENDS////////////////////////////////////////////////////////////////

    /**
     *Function that takes no parameters and is mainly for displaying your friends in a single conversation in a contact list and their status 
     * (Online - Offline - Busy -Away).
     */
   public void listFriends() {
        ListFriends.getItems().clear();
        userDAO d2 = new userDAO();
        FriendDAO d1 = new FriendDAO();
        users = new ArrayList();
        users = d1.getFriends(user.getId());
        Platform.runLater(new Runnable() {
            public void run() {
                for (String user1 : users) {
                    Label lbl = new Label();
                    lbl.setText(user1);
                    lbl.setFont(Font.font("Cambria", 20));
                    lbl.setTextFill(Color.web("#a77705"));
/////////////////////////////////////////Alaa/////////////////////////////////////////
                    Stat = d2.checkStatus(user1);
                    Circle c1 = new Circle();
                    c1.setRadius(8);
                    switch (Stat) {
                        case "offline":
                            c1.setFill(Color.GRAY);
                            break;
                        case "online":
                            c1.setFill(Color.GREEN);
                            break;
                        case "busy":
                            c1.setFill(Color.RED);
                            break;
                        case "away":
                            c1.setFill(Color.YELLOW);
                            break;
                    }
                    lbl.setOnMousePressed((event) -> {
                        updatetabs(lbl.getText());
                        upatenamelist();
                    });
/////////////////////////////////////////////////////////////////////////////////////////////
                    HBox hbox = new HBox();

                    hbox.setStyle("-fx-background-color: #331339;" + "-fx-padding: 15;" + "-fx-spacing: 10;" + "-fx-background-radius: 20px;");

                    hbox.getChildren().addAll(lbl, c1);

                    //   lbl.setGraphic(new ImageView(path));
                    lbl.setText((String) user1);
                    ListFriends.getItems().add(hbox);
                }
            }
        });
//
    }
    ////////////////////////////////////////////////////////////////////////////////////////////   

    /**
     *Function that takes no parameters and is mainly for selecting your friends to add in a group conversation.
     */
    public void groupFriends() {
        group.getItems().clear();
        userDAO d2 = new userDAO();
        FriendDAO d1 = new FriendDAO();
        users = new ArrayList();
        users = d1.getFriends(user.getId());
        Platform.runLater(new Runnable() {
            public void run() {

                for (String user1 : users) {
                    Label lbl = new Label();
                    CheckBox checkBox = new CheckBox();
                    lbl.setText(user1);
                    lbl.setFont(Font.font("Cambria", 20));
                    lbl.setTextFill(Color.web("#a77705"));
                    if (checkBox.isSelected()) {
                        groups.add(lbl.getText());
                    }
                    HBox hbox = new HBox();
                    hbox.setStyle("-fx-background-color: #331339;" + "-fx-padding: 15;" + "-fx-spacing: 10;" + "-fx-background-radius: 20px;");
                    hbox.getChildren().addAll(lbl, checkBox);
                    //   lbl.setGraphic(new ImageView(path));
                    lbl.setText((String) user1);
                    group.getItems().add(hbox);
                }
            }
        });
//
    }

//////////////////////////////////////////Shawarbyyy////////////////////////////////////////////////////
    /**
     * function that takes no parameteres but called when the fontsize combobox changed 
     * and changed the size;
     */
    @FXML
    private void chooseFontSize() {

        size = comboBoxSize.getSelectionModel().getSelectedItem();
        System.out.println(size);
    }
    
    
    /**
     * function that called when font familyfamily combobox changed
     */
    @FXML
    private void chooseFontFamily() {

        fontfamily = comboboxfont.getSelectionModel().getSelectedItem();
        System.out.println(fontfamily);
    }
    /**
     * function that called when the color in the coolor combo box changed, to change the color
     */
    @FXML
    private void chooseColor() {

        textColor = comboBoxColor.getSelectionModel().getSelectedItem();
        //System.out.println(fontfamily);
    }
    
    /**
     * 
     * @param name function that takes string name and input 
     * and create a new tab if there is no tab with this name 
     * and if the name of the tab is already exists the focus changed to the selected name
     */
    @FXML
    public void updatetabs(String name) {
        System.out.println(name);
        names = new ArrayList<>();
        names.add(name);
        names.add(user.getUsername());
        System.out.println("sa");
        int flag = 0;
        int counter = 0;
        ObservableList<Tab> observableList = chattabpane.getTabs();
        for (int i = 0; i < observableList.size(); i++) {
            if (observableList.get(i).getText().equals(name)) {
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            ScrollPane scrool = new ScrollPane(new VBox());
            scrool.setFitToWidth(true);
            Tab t1 = new Tab(name, scrool);
            chattabpane.getTabs().add(t1);
            tabnames.put(name, t1);
            chattabpane.getSelectionModel().select(tabnames.get(name));
        } else {
            chattabpane.getSelectionModel().select(tabnames.get(name));
        }
    }
    
    
    /**
     * function the called when clicked on the send button and get the text in the text field and send it 
     * to the server using server reference. and change the text field into blank.
     */
    public void getMsg() {

        String msg = tex_f.getText();
        if (msg.trim().length() > 0) {
            try {
                System.out.println(Ref.toString());
                Ref.tellToOther(msg, names, user.getUsername(), Colorstext.get(textColor), size, fontfamily);
                tex_f.setText("");
            } catch (RemoteException ex) {
                Logger.getLogger(chatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
   
    /**
     * function that updates the arraylist of the names to the user and the selected tab name
     */
    @FXML
    private void upatenamelist() {
        Tab temp = chattabpane.getSelectionModel().getSelectedItem();
        int flag = 0;
        String tempo = null;
        for (String element : tabnames.keySet()) {
            if (element.equals(temp.getText())) {
                flag = 1;
                tempo = element;
            }
            if (flag == 1) {
                break;
            }
        }
        names = new ArrayList<>();
        System.out.println(tempo);
        names.add(tempo);
        names.add(user.getUsername());  ///// name of user
        System.out.println("updated");
        lastSender = tablastsender.get(temp);
    }
   
    
    /**
     * 
     * @return arraylist of msgs of selected tab
     */
     public  ArrayList Arylst() {
        String tabname = names.get(0);
        for (String element : xmltab.keySet()) {
            if (element.equalsIgnoreCase(tabname)) {
                return xmltab.get(element);
            }
        }
        System.out.println("empty list");
        return null;
    }
    
    /**
     * 
     * @param msg 
     * @param Sender
     * @param color
     * @param Size
     * @param fontFamily 
     * fuction that add new msg into the array list of msgs of each tab 
     * and print the msg if you are the sender on the left 
     * and check the name of the last sender of the selected tab
     */
    public void PrintSender(String msg, String Sender, String color, int Size, String fontFamily) {
        message = new msg(Size, Sender, names.get(0), color, msg, fontFamily);
        int f = 0;
        for (String name : xmltab.keySet()) {
            if (name.equalsIgnoreCase(names.get(0))) {
                xmltab.get(name).add(message);
                System.out.println("First msg saved  :" + msg + "  " + names.get(0));
                f = 1;
            }
        }
        if (f == 0) {
            ArrayList<msg> templist = new ArrayList<msg>();
            templist.add(message);
            xmltab.put(names.get(0), templist);
            System.out.println("second msg saved  :" + msg + "      " + names.get(0));
        }
        Tab t = tabnames.get(names.get(0));  //name of reciever instead of index  0
        ScrollPane pane = (ScrollPane) t.getContent();
        VBox j = (VBox) pane.getContent();
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                if (lastSender != null && lastSender.equals(Sender)) {
                    System.out.println(lastSender + "first time");
                    HBox hBox = new HBox();
                    Text l9 = new Text(msg);
                    l9.setFont(new Font(fontfamily, Size));
                    l9.setFill(reverseColorstext.get(color));
                    TextFlow l = new TextFlow(l9);

                    hBox.getChildren().add(l);
                    hBox.setAlignment(Pos.BASELINE_LEFT);
                    j.getChildren().add(hBox);
                    j.setSpacing(1);
                } else {
                    lastSender = Sender;
                    tablastsender.put(t, Sender);
                    HBox hBox2 = new HBox();
                    Label ll = new Label(Sender);
                    ll.setFont(new Font(fontfamily, 26));
                    hBox2.getChildren().add(ll);
                    hBox2.setAlignment(Pos.BASELINE_LEFT);
                    j.getChildren().add(hBox2);
                    j.setSpacing(1);

                    HBox hBox = new HBox();
                    Text l9 = new Text(msg);
                    l9.setFont(new Font(fontfamily, Size));
                    l9.setFill(reverseColorstext.get(color));
                    TextFlow l = new TextFlow(l9);
                    hBox.getChildren().add(l);
                    hBox.setAlignment(Pos.BASELINE_LEFT);
                    j.getChildren().add(hBox);
                    j.setSpacing(1);
                }
            }
        });

    }
    
    /**
     * 
     * @param msg
     * @param Sender
     * @param color
     * @param Size
     * @param fontFamily 
     *fuction that add new msg into the array list of msgs of each tab 
     * and print the msg if you are the reciever on the right 
     * and check the name of the last sender of the selected tab
     */
    public void PrintReciever(String msg, String Sender, String color, int Size, String fontFamily) {
        message = new msg(Size, Sender, names.get(0), color, msg, fontFamily);
        int f = 0;
        for (String name : xmltab.keySet()) {
            if (name.equalsIgnoreCase(Sender)) {
                xmltab.get(name).add(message);
                f = 1;
            }
        }
        if (f == 0) {
            ArrayList<msg> templist = new ArrayList<msg>();
            templist.add(message);
            xmltab.put(names.get(0), templist);
        }

        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                int flag = 0;
                int counter = 0;
                ObservableList<Tab> observableList = chattabpane.getTabs();
                for (int k = 0; k < observableList.size(); k++) {
                    if (observableList.get(k).getText().equals(Sender)) {
                        flag = 1;
                        counter++;
                        break;
                    }
                }
                if (flag == 0) {
                    ScrollPane scrool = new ScrollPane(new VBox());

                    Tab t1 = new Tab(Sender, scrool);
                    chattabpane.getTabs().add(t1);
                    tabnames.put(Sender, t1);
                    chattabpane.getSelectionModel().select(tabnames.get(Sender));
                    upatenamelist();
                } else {
                    chattabpane.getSelectionModel().select(tabnames.get(Sender));
                    upatenamelist();
                }

                Tab t = tabnames.get(Sender);        //name of reciever instead of index  0 ta2reeeban ya3ny
                ScrollPane pane = (ScrollPane) t.getContent();
                VBox j = (VBox) pane.getContent();

                if (lastSender != null && lastSender.equals(Sender)) {

                    HBox hBox = new HBox();
                    Text l9 = new Text(msg);
                    l9.setFont(new Font(fontfamily, Size));
                    l9.setFill(reverseColorstext.get(color));
                    TextFlow l = new TextFlow(l9);

                    hBox.getChildren().add(l);
                    hBox.setAlignment(Pos.BASELINE_RIGHT);
                    j.getChildren().add(hBox);
                    j.setSpacing(1);
                } else {
                    lastSender = Sender;
                    tablastsender.put(t, Sender);
                    HBox hBox2 = new HBox();
                    Label ll = new Label(Sender);
                    ll.setFont(new Font(fontfamily, Size + 3));
                    hBox2.getChildren().add(ll);
                    hBox2.setAlignment(Pos.BASELINE_RIGHT);
                    j.getChildren().add(hBox2);
                    j.setSpacing(1);

                    HBox hBox = new HBox();
                    Text l9 = new Text(msg);
                    l9.setFont(new Font(fontfamily, Size));
                    l9.setFill(reverseColorstext.get(color));
                    TextFlow l = new TextFlow(l9);
                    hBox.getChildren().add(l);
                    hBox.setAlignment(Pos.BASELINE_RIGHT);
                    j.getChildren().add(hBox);
                    j.setSpacing(1);
                }
            }
        });
    }
  /**
     *
     * 
     * add messages to ArrayList to save them as XML.
     * 
     */

    @FXML
    void saveXmlBttn(ActionEvent event) {
        Platform.runLater(() -> {

            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            save = new SaveXML();
            save.saveXMLFile(Arylst());
            System.out.println("d5l !!!");
            System.out.println();
            //   }
        });
    }

}
