package domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
public class Student {
    @Id
    private int id;
    private String name;
    private String email;
    private String phone;
    @Embedded
    private Address address;
}
