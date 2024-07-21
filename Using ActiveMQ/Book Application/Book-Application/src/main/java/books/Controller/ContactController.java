// src/main/java/contacts/Controller/ContactController.java
package books.Controller;

import books.Model.Contacts;
import books.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<List<Contacts>> getAllContacts() {
        List<Contacts> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<Contacts> getContactByFirstName(@PathVariable String firstName) {
        Contacts contact = contactService.getContactByFirstName(firstName);
        return ResponseEntity.ok(contact);
    }

    @PostMapping
    public ResponseEntity<Contacts> saveContact(@RequestBody Contacts contact) {
        Contacts savedContact = contactService.saveContact(contact);
        return ResponseEntity.ok(savedContact);
    }

    @PutMapping("/{firstName}")
    public ResponseEntity<Contacts> updateContact(@PathVariable String firstName, @RequestBody Contacts contact) {
        contact.setFirstName(firstName);
        Contacts updatedContact = contactService.updateContact(contact);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{firstName}")
    public ResponseEntity<String> deleteContact(@PathVariable String firstName) {
        contactService.deleteContact(firstName);
        return ResponseEntity.ok("Contact deleted");
    }
}
