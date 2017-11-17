package com.sboot.crud;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.sboot.crud.model.Address;
import com.sboot.crud.model.Contact;
import com.sboot.crud.model.Employee;
import com.sboot.crud.model.Position;
import com.sboot.crud.repository.AddressRepository;
import com.sboot.crud.repository.EmployeeRepository;

@Transactional

@SpringBootApplication
public class EmployeeManagementApplication implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        createEmployeeSampleData();

    }

    private void createEmployeeSampleData() {
        Employee employee = new Employee();
        employee.setFirstName("Marian");
        employee.setLastName("Wojcik");
        employee.setBirthdate(Date.valueOf("1990-01-01"));
        employee.addAddress(createAddressSampleData());
        employee.setContactData(creatContactSampleData());
        employee.setPosition(createPositionSampleData());
        employee.setSalary(5000.0);
        employeeRepository.save(employee);

    }

    private Address createAddressSampleData() {
        Address address = new Address();
        address.setStreetName("Test St.");
        address.setHouseNumber("1a");
        address.setHomeNumber("2");
        address.setPostalCode("43-250");
        address.setCityName("Wroclaw");
        // addressRepository.save(address);
        return address;
    }

    private Contact creatContactSampleData() {
        Contact contactData = new Contact();
        contactData.setEmail("test@test.com");
        contactData.setPhoneNumber("+48444555666");
        return contactData;
    }

    private Position createPositionSampleData() {
        Position position = new Position();
        position.setPositionName("Developer");

        return position;
    }

}
