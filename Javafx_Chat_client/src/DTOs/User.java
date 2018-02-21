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
 * 
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

    /**
     *
     * @return
     */
    public int getAge() {
        return Age;
    }

    /**
     *
     * @param Age
     */
    public void setAge(int Age) {
        this.Age = Age;
    }

    /**
     *
     * @param username
     * @param email
     * @param password
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     *
     */
    public User() {
    }

    /**
     *
     * @param username
     * @param email
     * @param password
     * @param id
     * @param gender
     * @param country
     * @param status
     * @param phoneNum
     * @param isonline
     * @param isappoffline
     * @param profilepic
     */
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
    
    /**
     *
     * @return
     */
    public String isIsonline() {
        return isonline;
    }

    /**
     *
     * @param isonline
     */
    public void setIsonline(String isonline) {
        this.isonline = isonline;
    }

    /**
     *
     * @return
     */
    public String isIsappoffline() {
        return isappoffline;
    }

    /**
     *
     * @param isappoffline
     */
    public void setIsappoffline(String isappoffline) {
        this.isappoffline = isappoffline;
    }

    /**
     *
     * @return
     */
    public Image getProfilepic() {
        return profilepic;
    }

    /**
     *
     * @param profilepic
     */
    public void setProfilepic(Image profilepic) {
        this.profilepic = profilepic;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }
    
    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @return
     */
    public String getPhoneNum() {
        return phoneNum;
    }
    
    
}
