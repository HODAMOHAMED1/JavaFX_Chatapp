/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import chat.generalCRUD;
import java.util.ArrayList;

/**
 *
 * @author Alaa
 */
public interface user_has_request_inter extends generalCRUD<user_has_request> {

    @Override
    public int delete(user_has_request element);

    public user_has_request retrieve(user_has_request element);

    @Override
    public int update(user_has_request element);

    @Override
    public int create(user_has_request element);

    public ArrayList<Integer> getId(int receiver);

    public ArrayList getUsers(int receiver);
    public void updateStatus(int from ,int to , String stat);

}
