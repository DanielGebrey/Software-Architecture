package books.Integration;

import books.Model.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PersonMessageListener {

    @JmsListener(destination = "personQueue")
    public void receiveMessage(String contactAString) {
        System.out.println("Received Message: " + contactAString);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Contacts contacts = objectMapper.readValue(contactAString, Contacts.class);

            System.out.println("Jms received: "+contacts);
        } catch (Exception e) {
            System.out.println("Jms cannot receive Contacts " + e);
        }
        
    }

}
