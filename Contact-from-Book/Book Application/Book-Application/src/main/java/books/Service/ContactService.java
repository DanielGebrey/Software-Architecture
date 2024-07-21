// src/main/java/contacts/Service/ContactService.java
package books.Service;

import books.Model.Contacts;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private List<Contacts> contacts = new ArrayList<>();

    public List<Contacts> getAllContacts() {
        return contacts;
    }

    public Contacts getContactByFirstName(String firstName) {
        return contacts.stream().filter(c -> c.getFirstName().equals(firstName)).findFirst().orElse(null);
    }

    public Contacts saveContact(Contacts contact) {
        contacts.add(contact);
        return contact;
    }

    public Contacts updateContact(Contacts contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getFirstName().equals(contact.getFirstName())) {
                contacts.set(i, contact);
                return contact;
            }
        }
        return null;
    }

    public void deleteContact(String firstName) {
        contacts.removeIf(c -> c.getFirstName().equals(firstName));
    }
}
