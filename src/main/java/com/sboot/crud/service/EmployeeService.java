package com.sboot.crud.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.crud.model.Address;
import com.sboot.crud.model.Contact;
import com.sboot.crud.model.Employee;
import com.sboot.crud.model.Position;
import com.sboot.crud.model.Report;
import com.sboot.crud.repository.AddressRepository;
import com.sboot.crud.repository.EmployeeRepository;

@Transactional
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Employee> getAllEmployees() {
        return Lists.newArrayList(employeeRepository.findAll());
    }

    public Iterable<Employee> getAllEmployeesIterable() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findOne(id);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.delete(id);

    }

    public void createEmployeeData(String firstName, String lastName,
            String birthDate, String streetName,
            String houseNumber, String homeNumber,
            String postalCode, String city, String phone,
            String email, String position, Double salary) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setBirthdate(Date.valueOf(birthDate));
        employee.addAddress(createAddressData(streetName, houseNumber, homeNumber, postalCode, city));
        employee.setContactData(creatContactData(phone, email));
        employee.setPosition(createPositionData(position));
        employee.setSalary(salary);
        employeeRepository.save(employee);

    }

    private Address createAddressData(String streetName, String houseNumber, String homeNumber, String postalCode, String city) {
        Address address = new Address();
        address.setStreetName(streetName);
        address.setHouseNumber(houseNumber);
        address.setHomeNumber(homeNumber);
        address.setPostalCode(postalCode);
        address.setCityName(city);
        return address;
    }

    private Contact creatContactData(String phone, String email) {
        Contact contactData = new Contact();
        contactData.setEmail(email);
        contactData.setPhoneNumber(phone);
        return contactData;
    }

    private Position createPositionData(String position) {
        Position positionObject = new Position();
        positionObject.setPositionName(position);
        return positionObject;
    }

    public void updateEmployeeData(Long id, String firstName, String lastName, String birthDate, String streetName, String houseNumber, String homeNumber,
            String postalCode, String city, String phone, String email, String position, Double salary) {
        Employee employee = getEmployeeById(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setBirthdate(Date.valueOf(birthDate));
        employee.addAddress(updateAddressData(id, streetName, houseNumber, homeNumber, postalCode, city));
        employee.setContactData(creatContactData(phone, email));
        employee.setPosition(createPositionData(position));
        employee.setSalary(salary);

        employeeRepository.save(employee);
    }

    private Address updateAddressData(Long id, String streetName, String houseNumber, String homeNumber, String postalCode, String city) {
        Address address = addressRepository.findOne(id);
        address.setStreetName(streetName);
        address.setHouseNumber(houseNumber);
        address.setHomeNumber(homeNumber);
        address.setPostalCode(postalCode);
        address.setCityName(city);
        return address;
    }

    public Report createReport() {

        final Integer YEAR_IN_MONTHS = 12;
        List<Employee> employeeList = createEmployeeList(getAllEmployeesIterable());
        final Comparator<Employee> comp = (p1, p2) -> Double.compare(p1.getSalary(), p2.getSalary());

        Employee lowestSalaryEmployee = Collections.min(employeeList, comp);
        Employee highestSalaryEmployee = Collections.max(employeeList, comp);

        Report report = new Report();
        report.setAvgSalary(employeeList.stream().mapToDouble(employee -> employee.getSalary()).average().getAsDouble());
        report.setMaxSalary(highestSalaryEmployee.getSalary());
        report.setMinSalary(lowestSalaryEmployee.getSalary());
        report.setLowestPaidEmployeeName(lowestSalaryEmployee.getFirstName() + ' ' + lowestSalaryEmployee.getLastName());
        report.setLowestPaidEmployeePosition(lowestSalaryEmployee.getPosition().getPositionName());
        report.setMaxSalary(lowestSalaryEmployee.getSalary());
        report.setHighestPaidEmployeeName(highestSalaryEmployee.getFirstName() + ' ' + highestSalaryEmployee.getLastName());
        report.setHighestPaidEmployeePosition(highestSalaryEmployee.getPosition().getPositionName());
        report.setAvgSalaryPerYear(report.getAvgSalary() * YEAR_IN_MONTHS);
        report.setTotalIncomePerYear(YEAR_IN_MONTHS * (employeeList.stream().mapToDouble(salary -> salary.getSalary()).sum()));

        return report;
    }

    private List<Employee> createEmployeeList(Iterable<Employee> employeeIterable) {
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : employeeIterable) {
            employeeList.add(employee);
        }

        return employeeList;
    }

}
