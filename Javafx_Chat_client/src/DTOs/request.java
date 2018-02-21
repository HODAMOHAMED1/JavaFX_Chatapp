/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * 
 * 
 */
public class request {
    private int req_Id ;
    private int reciver_id ; 
    private String status ;

    /**
     *
     */
    public request() {
    }

    /**
     *
     * @param reciver_id
     */
    public void setReciver_id(int reciver_id) {
        this.reciver_id = reciver_id;
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
     * @param req_Id
     */
    public void setReq_Id(int req_Id) {
        this.req_Id = req_Id;
    }

    /**
     *
     * @return
     */
    public int getReciver_id() {
        return reciver_id;
    }

    /**
     *
     * @return
     */
    public int getReq_Id() {
        return req_Id;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

  
    
    
    
}
