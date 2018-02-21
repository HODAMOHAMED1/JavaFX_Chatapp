
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import RMI.msg;
import XML.demo.FontFamily;
import XML.demo.Message;
import XML.demo.MymessageType;
import XML.demo.ObjectFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * 
 */
public class SaveXML {

    public SaveXML() {

    }
/**
     * saveXMLFile Method used JAXB which defines an API for reading and writing Java objects to and from XML documents.
     * @param messages 
     */

    public void saveXMLFile( ArrayList<msg> messages) {
        try {
            // TODO code application logic here
            JAXBContext context = JAXBContext.newInstance("XML.demo");
            Unmarshaller unmarsh = context.createUnmarshaller();
            JAXBElement JAXBPerson = (JAXBElement) unmarsh.unmarshal(new File("src/XML/message.xml"));
            Message msg = (Message) JAXBPerson.getValue();
            MymessageType msgs = (MymessageType) msg.getMymessage().get(1);
            ObjectFactory factory = new ObjectFactory();
            Message msgObj = factory.createMessage();
//            System.out.println("from=" + msgs.getFrom());
//            System.out.println("to=" + msgs.getTo().get(0));
//            System.out.println("color=" + msgs.getColor());
//            System.out.println("size=" + msgs.getSize());
//            System.out.println("Family=" + msgs.getFamily());
//            System.out.println("body=" + msgs.getBody());  
            if (messages.isEmpty()) {
                System.out.println("fadya !!!");
            }
            for (msg msg1 : messages) {

                //  Update
                MymessageType msgg = factory.createMymessageType();
                msgg.setFrom(msg1.getFrom());

                //msgg.setTO();
                msgg.setFamily(FontFamily.fromValue(msg1.getFontfamily()));
                msgg.setColor(msg1.getColor());
                msgg.setSize(msg1.getFontsize());
                msgg.setBody(msg1.getBody());
                msgObj.addMsg(msgObj, msgg);
            }
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marsh.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "message.xsd");
            //-------------- set XLST ------------------
            marsh.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                    "<?xml-stylesheet type='text/xsl' href='message.xsl'?>");           
            JAXBElement on = factory.createMessage(msgObj);
            marsh.marshal(on, new FileOutputStream("C:\\Users\\Freeware Sys\\Desktop\\19-1\\Javafx_Chat_shawrby_update\\src\\XML\\output.xml"));
      //      FileOutputStream fileOutputStream = new FileOutputStream(file);
         //   marsh.marshal(on, fileOutputStream);
        //    fileOutputStream.close();
        //     copyFile(new File("src//XML//message.xsl"),file);
     //       copyFile(getClass().getResource("/XML/message.xsl").openStream(), file.getParent() +"/message.xsl");
    //        copyFile(getClass().getResource("/XML/message.xsd").openStream(), file.getParent() +"/message.xsd");
//
//            try {
//                marsh.marshal(on, new FileOutputStream("output.xml"));
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(messagejax.class.getName()).log(Level.SEVERE, null, ex);
//            }
        } catch (JAXBException ex) {
            Logger.getLogger(messagejax.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SaveXML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
