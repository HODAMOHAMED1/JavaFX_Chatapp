/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

/**
 *
 * This class is used to take parameters of the messages in order to save it .
 * 
 */
public class msg {
    
    private int fontsize;
    private String from;
    private String to;
    private String color;
    private String body;
    private String fontfamily;

    /**
     *
     * @param fontsize
     * @param from
     * @param to
     * @param color
     * @param body
     * @param fontfamily
     */
    public msg(int fontsize, String from, String to, String color, String body, String fontfamily) {
        this.fontsize = fontsize;
        this.from = from;
        this.to = to;
        this.color = color;
        this.body = body;
        this.fontfamily = fontfamily;
    }

    /**
     *
     * @return
     */
    public int getFontsize() {
        return fontsize;
    }

    /**
     *
     * @return
     */
    public String getFrom() {
        return from;
    }

    /**
     *
     * @return
     */
    public String getTo() {
        return to;
    }

    /**
     *
     * @return
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @return
     */
    public String getFontfamily() {
        return fontfamily;
    }

    /**
     *
     * @param fontsize
     */
    public void setFontsize(int fontsize) {
        this.fontsize = fontsize;
    }

    /**
     *
     * @param from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     *
     * @param to
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @param fontfamily
     */
    public void setFontfamily(String fontfamily) {
        this.fontfamily = fontfamily;
    }
    
    
    
}
