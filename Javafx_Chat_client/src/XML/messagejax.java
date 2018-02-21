/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import XML.demo.FontFamily;
import XML.demo.Message;
import XML.demo.MymessageType;
import XML.demo.ObjectFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author hoda.CO
 */
public class messagejax {
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            JAXBContext context = JAXBContext.newInstance("XML.demo");
            Unmarshaller unmarsh = context.createUnmarshaller();
            JAXBElement JAXBPerson =(JAXBElement)unmarsh.unmarshal(new File("src/XML/message.xml"));
            Message msg = (Message)JAXBPerson.getValue();
            MymessageType msgs = (MymessageType) msg.getMymessage().get(1);
//            System.out.println("from=" + msgs.getFrom());
//            System.out.println("to=" + msgs.getTo().get(0));
//            System.out.println("color=" + msgs.getColor());
//            System.out.println("size=" + msgs.getSize());
//            System.out.println("Family=" + msgs.getFamily());
//            System.out.println("body=" + msgs.getBody());  
            // Update
            
            ObjectFactory factory = new ObjectFactory();
            Message msgObj = factory.createMessage();
            MymessageType msgg = factory.createMymessageType();
            msgg.setFrom("hoda");
            //msgg.setTO();
            msgg.setFamily(FontFamily.ARIAL);
            msgg.setColor("red");
            msgg.setSize(10);
            msgg.setBody("hi ");
            msgObj.addMsg(msgObj,msgg);
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);                       
             JAXBElement on = factory.createMessage(msgObj);
            try {  
                marsh.marshal(on,new FileOutputStream("output.xml"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(messagejax.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JAXBException ex) {
            Logger.getLogger(messagejax.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
