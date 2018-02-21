/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

/**
 *
 * @author Alaa
 */
public class user_has_request {

    private int req_id;
    private int from;
    private int to;
public user_has_request()
{
    
}
    public user_has_request(int req_id, int from, int to) {
        this.req_id = req_id;
        this.from = from;
        this.to = to;
    }

    public int getReq_id() {
        return req_id;
    }

    public void setReq_id(int req_id) {
        this.req_id = req_id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    
}
