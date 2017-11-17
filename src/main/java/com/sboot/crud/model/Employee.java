package com.sboot.crud.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;
    //
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    private Contact contact;

    private Position position;

    private Double salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthDate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthDate = birthdate;
    }

    public List<Address> getAddressList() {
        return addresses;
    }

    public void setAddressList(List<Address> addressList) {
        this.addresses = addressList;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setEmployee(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setEmployee(null);
    }

    public Contact getContactData() {
        return contact;
    }

    public void setContactData(Contact contactData) {
        this.contact = contactData;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthdate=" + birthDate + ", addressList=" + addresses
                + ", contactData=" + contact + ", position=" + position + ", salary=" + salary + "]";
    }

}
