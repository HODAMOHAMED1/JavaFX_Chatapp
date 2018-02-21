/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverchatproject;

import DTOs.User;
import Daos.userDAO;
import Interfaces.ClientInt;
import Interfaces.ServerInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author 3alilio
 */
public class ServerImplementation extends UnicastRemoteObject implements ServerInt {

    static HashMap<String, ClientInt> OnlineUsers = new HashMap<>();
    static HashMap<Integer, ArrayList<String>> chatIds = new HashMap<>();
    userDAO userdata = new userDAO();

    public static HashMap<String, ClientInt> getOnlineUsers() {
        return OnlineUsers;
    }

    private static int checkArrayList(ArrayList<String> test) {

        for (Integer id : chatIds.keySet()) {
            int counter = 0;
            for (String element : chatIds.get(id)) {

                for (String item : test) {
                    if (item.equalsIgnoreCase(element)) {
                        counter++;
                    }
                }

            }
            if(counter==test.size())return id;
        }
        return -1;
    }

    public ServerImplementation() throws RemoteException {
    }

    @Override
    public void unregister(String username) throws RemoteException {
        OnlineUsers.remove(username);
        System.out.println(username + " is offline");
    }

    @Override
    public void register(String username, ClientInt clientRef) throws RemoteException {
        OnlineUsers.put(username, clientRef);
        System.out.println(username + " is online");
    }

    @Override
    public void tellToOther(String msg, ArrayList<String> Users, String Sender, String color, int size, String fontFamily) throws RemoteException {
        System.out.println(msg+ Sender+ color+ size);
        int chat_id = checkArrayList(Users);
        if(chat_id==-1){
            //Add new chat id in table
        }
        for (int i = 0; i < Users.size(); i++) {
            for (String name : OnlineUsers.keySet()) {
                if (name.equalsIgnoreCase(Users.get(i)) && name.equalsIgnoreCase(Sender)) {
                    OnlineUsers.get(name).receiveSender(msg,  Sender, color,size,fontFamily);
                } else if (name.equalsIgnoreCase(Users.get(i)) && !name.equalsIgnoreCase(Sender)) {
                    OnlineUsers.get(name).receiveReciever(msg, Sender, color,size, fontFamily);
                }
            }
        }
    }

    @Override
    public int isemailexists(String email) throws RemoteException {
        return userdata.isemailexists(email);

    }

    @Override
    public List<User> getContactList(String username) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User signin(String email, String passwword) throws RemoteException {
        User ahmed = new User();
        ahmed.setEmail(email);
        ahmed.setPassword(passwword);
        return userdata.retrieve(ahmed);
    }

    @Override
    public void notifyOnline(String msgfromusponsor) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientInt getClientObj(String sender, String Reciever) throws RemoteException {
        for(String element : OnlineUsers.keySet())
        {
            if(OnlineUsers.get(element).equals(Reciever))
            {
                return OnlineUsers.get(element);
            }
        }
        return null;
    }

    public int getUserId(String username) {
     return userdata.getUserId(username);
    }

    public User getUser(String mail) {
     return userdata.getUser(mail);
    }
    
    public User retrieve(User user) {
      return userdata.retrieve(user);
    }

    public int update(User user_temp) {
         return userdata.update(user_temp);
    }

}
