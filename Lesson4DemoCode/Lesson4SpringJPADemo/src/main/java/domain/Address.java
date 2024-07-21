package domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;

@Embeddable
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String zipCode;

}
