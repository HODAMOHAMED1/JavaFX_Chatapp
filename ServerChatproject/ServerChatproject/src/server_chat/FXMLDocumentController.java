/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_chat;

import Daos.DaoMain;
import Daos.userDAO;
import Interfaces.ClientInt;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.sound.midi.Soundbank;
import org.controlsfx.control.Notifications;
import serverchatproject.ServerChatproject;
import serverchatproject.ServerImplementation;

/**
 * FXML Controller class
 *
 * @author hoda.CO
 */
public class FXMLDocumentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TabPane tab_pane;
    @FXML
    private Tab t1;
    @FXML
    private Tab t2;
    @FXML
    private Tab t3;
    @FXML
    private Tab t4;
    @FXML
    private TextField text;
    @FXML
    private Text username;
    @FXML
    private Text lblid;
    @FXML
    private Text lblmail;
    @FXML
    private Text lblcountry;
    @FXML
    private Text lblgender;
    @FXML
    private Text lblstatus;
    @FXML
    private TextField text2;
    @FXML
    private Button b;

    @FXML
    private ImageView startserver;

    @FXML
    private ImageView pauseserver;

    @FXML
    private BorderPane an;

    private int flag = 0;

    @FXML
    TextField ipAddress;

    @FXML
    private ListView<String> emails;

    @FXML
    private Button search;

    private ServerChatproject projectServer;
    ArrayList user = new ArrayList();
    userDAO d1 = new userDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            String ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println(ip);
            ipAddress.setText(ip);
            ipAddress.setEditable(false);
        } catch (UnknownHostException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            ipAddress.setText("Can't get IPAdress");
            ipAddress.setEditable(false);
        }
    }

    @FXML
    public void setstatistics() {
//        tab_pane.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable,
//                                                                        Tab oldValue, Tab newValue) -> {
//             if (newValue == t4)
//             {

        Node[] scenes = new Node[3];
        Pagination pages = new Pagination(3);

        PieChart pieChartstatus = new PieChart();
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("offline", d1.selectOffline()));
        data.add(new PieChart.Data("Away", d1.selectAway()));
        data.add(new PieChart.Data("online", d1.selectOnline()));
        data.add(new PieChart.Data("Busy", d1.selectBusy()));

        pieChartstatus.setData(data);

        PieChart pieChartGender = new PieChart();
        ObservableList<PieChart.Data> data1 = FXCollections.observableArrayList();
        data1.add(new PieChart.Data("Male older than 18 years old", d1.selectGenderAgeGreater("Male", 18)));
        data1.add(new PieChart.Data("Female younger than 18 years old", d1.selectGenderAgeSmaller("Female", 18)));

        pieChartGender.setData(data1);

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Users");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Numbers");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("User Genders");

        dataSeries1.getData().add(new XYChart.Data("Female", d1.selectFemales()));
        dataSeries1.getData().add(new XYChart.Data("Male", d1.selectMales()));
        dataSeries1.getData().add(new XYChart.Data("Total Number of users", d1.selectAllSum()));

        barChart.getData().add(dataSeries1);

        pages.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                StackPane v = new StackPane(scenes[param]);
                return v;
            }
        });

        scenes[0] = pieChartGender;
        scenes[1] = pieChartstatus;
        scenes[2] = barChart;

        an.setCenter(pages);

//             }
//           
//        });
    }

    public void user() {
        String x = text.getText();
        user = new ArrayList<>();
        user = d1.retrieveServer(x);
        if (user.size() == 5) {
            lblmail.setText(String.valueOf(user.get(0)));
            lblid.setText(String.valueOf(user.get(1)));
            lblcountry.setText(String.valueOf(user.get(2)));
            lblgender.setText(String.valueOf(user.get(3)));
            lblstatus.setText(String.valueOf(user.get(4)));
            text.setText("");
            System.out.println("user called");
            System.out.println(String.valueOf(user.get(4)));
            System.out.println(String.valueOf(user.get(3)));
            System.out.println(String.valueOf(user.get(2)));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Can't load Data");
            alert.showAndWait();
        }
    }

//.setOnAction((event) -> {
//    // Button was clicked, do something...
//    outputTextArea.appendText("Button Action\n");
//});
    public void sendNotification() {
//        tab_pane.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable,
//                                                                        Tab oldValue, Tab newValue) -> {
//             if (newValue == t2)
//             {
        String msg = text2.getText().trim();

        if (msg.length() != 0) {
            HashMap<String, ClientInt> OnlineUsers = ServerImplementation.getOnlineUsers();
            if (OnlineUsers.keySet().size() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No one is online to send");
                alert.showAndWait();
            }
            for (String element : OnlineUsers.keySet()) {
                try {
                    OnlineUsers.get(element).recieveNotSer(msg);
                } catch (RemoteException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Can't send Notification right now");
                    alert.showAndWait();
                }
            }
        }
        text2.setText("");

//             }
//           
//        });
    }
//    public void sendannounce()
//    {
//         Notifications not = Notifications.create()
//                .title("Ichat").text("user 2 is online").graphic(null).hideAfter(Duration.seconds(10))
//                .position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.err.println("Notification Clicked !!!!");
//            }
//        });
//        not.showConfirm();
//        not.darkStyle();
//        
//    }

    @FXML
    private void startServer() {

        if (flag == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Server already running");
            alert.showAndWait();
        } else {
            projectServer = new ServerChatproject();
            flag = 1;
        }
    }

    @FXML
    private void pauseServer() {

        if (flag == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Server already stopped");
            alert.showAndWait();
        } else {
            // reg.unbind(string);
            //   projectServer.close()
            flag = 0;
        }
    }

    @FXML
    public void loadTable() {
        userDAO dao = new userDAO();
        ObservableList<String> emailslist = FXCollections.observableArrayList();
        emailslist.add("Email");
        ArrayList<String> emailstotal = new ArrayList<>();
        emailstotal = dao.retrieveEmails();
        for (String element : emailstotal) {
            emailslist.add(element);
        }
        if (emailstotal.get(0).equalsIgnoreCase("error")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Can't load Data");
            alert.showAndWait();
        } else {
            emails.setItems(emailslist);
        }

    }
}
