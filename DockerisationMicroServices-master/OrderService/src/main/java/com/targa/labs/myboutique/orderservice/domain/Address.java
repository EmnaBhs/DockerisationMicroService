package com.targa.labs.myboutique.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Address.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Embeddable
public class Address implements Serializable {

    @Column(name = "address_1")
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "city")
    private String city;

    //@NotNull
    @Size(max = 10)
    @Column(name = "postcode", length = 10)//, nullable = false)
    private String postcode;

    //@NotNull
    @Size(max = 2)
    @Column(name = "country", length = 2)//, nullable = false)
    private String country;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
