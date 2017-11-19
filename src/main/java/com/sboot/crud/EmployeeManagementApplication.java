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

    private final String FIRST_NAME = "Marian";
    private final String LAST_NAME = "Wójcik";
    private final Date BIRTH_DATE = Date.valueOf("1990-01-01");
    private final String STREET_NAME = "Test St.";
    private final String HOUSE_NUMBER = "1a";
    private final String HOME_NUMBER = "2";
    private final String POSTAL_CODE = "43-250";
    private final String CITY = "Wroclaw";
    private final String PHONE_NUMBER = "+48444555666";
    private final String EMAIL = "test@test.com";
    private final String POSITION = "Developer";
    private final Double SALARY = 5000.0;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        /* Fill in-memory db with sample data of employee */
        createEmployeeSampleData();

    }

    private void createEmployeeSampleData() {

        Employee employee = new Employee();
        employee.setFirstName(FIRST_NAME);
        employee.setLastName(LAST_NAME);
        employee.setBirthdate(BIRTH_DATE);
        employee.addAddress(createAddressSampleData());
        employee.setContactData(creatContactSampleData());
        employee.setPosition(createPositionSampleData());
        employee.setSalary(SALARY);
        employeeRepository.save(employee);

    }

    private Address createAddressSampleData() {
        Address address = new Address();
        address.setStreetName(STREET_NAME);
        address.setHouseNumber(HOUSE_NUMBER);
        address.setHomeNumber(HOME_NUMBER);
        address.setPostalCode(POSTAL_CODE);
        address.setCityName(CITY);
        return address;
    }

    private Contact creatContactSampleData() {
        Contact contactData = new Contact();
        contactData.setEmail(EMAIL);
        contactData.setPhoneNumber(PHONE_NUMBER);
        return contactData;
    }

    private Position createPositionSampleData() {
        Position position = new Position();
        position.setPositionName(POSITION);

        return position;
    }

}
