/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.io.Serializable;
import javafx.scene.image.Image;

/**
 *
 * @author 3alilio
 */
public class User implements Serializable{
    private String username;
    private String email;
    private String password;
    private int id;
    private String gender;
    private String country;
    private String status;
    private String phoneNum;
    private String isonline;
    private String isappoffline;
    private Image profilepic;
      private int Age;

    public User(String mail, String password) {
        this.email = email;
        this.password = password;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public User(String username, String email, String password, int id, String gender, String country, String status, String phoneNum, String isonline, String isappoffline, Image profilepic) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.id = id;
        this.gender = gender;
        this.country = country;
        this.status = status;
        this.phoneNum = phoneNum;
        this.isonline = isonline;
        this.isappoffline = isappoffline;
        this.profilepic = profilepic;
    }
    
    public String isIsonline() {
        return isonline;
    }

    public void setIsonline(String isonline) {
        this.isonline = isonline;
    }

    public String isIsappoffline() {
        return isappoffline;
    }

    public void setIsappoffline(String isappoffline) {
        this.isappoffline = isappoffline;
    }

    public Image getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(Image profilepic) {
        this.profilepic = profilepic;
    }



    
    
    
    public String getUsername() {
        return username;
    }
    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getStatus() {
        return status;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
    
    
}