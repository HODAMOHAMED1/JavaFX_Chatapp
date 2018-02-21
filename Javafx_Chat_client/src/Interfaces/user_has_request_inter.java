/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import DTOs.user_has_request;
import Interfaces.generalCRUD;
import java.util.ArrayList;

/**
 *
 * 
 * @author Freeware Sys
 */
public interface user_has_request_inter extends generalCRUD<user_has_request> {

    @Override
    public int delete(user_has_request element);

    public user_has_request retrieve(user_has_request element);

    @Override
    public int update(user_has_request element);

    @Override
    public int create(user_has_request element);

    /**
     *
     * @param receiver
     * @return
     */
    public ArrayList<Integer> getId(int receiver);

    /**
     *
     * @param receiver
     * @return
     */
    public ArrayList getUsers(int receiver);

    /**
     *
     * @param from
     * @param to
     * @param stat
     */
    public void updateStatus(int from ,int to , String stat);

}
