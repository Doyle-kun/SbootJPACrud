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

import com.sboot.crud.controller.EmployeeParameters;
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

    public EmployeeParameters setEmployeeParamsForEdit(Long id) {
        EmployeeParameters employeeParameters = new EmployeeParameters();
        Employee employee = employeeRepository.findOne(id);
        employeeParameters.setId(employee.getId());
        employeeParameters.setFirstName(employee.getFirstName());
        employeeParameters.setLastName(employee.getLastName());
        employeeParameters.setBirthDate(employee.getBirthdate().toString());
        /* Multi-address support is currently disabled - some frontend issuess with form to add multiple adresses, but backend fully supports it */
        for (Address address : employee.getAddressList()) {
            employeeParameters.setStreetName(address.getStreetName());
            employeeParameters.setHouseNumber(address.getHouseNumber());
            employeeParameters.setHomeNumber(address.getHomeNumber());
            employeeParameters.setPostalCode(address.getPostalCode());
            employeeParameters.setCity(address.getCityName());
        }
        employeeParameters.setPhone(employee.getContactData().getPhoneNumber());
        employeeParameters.setEmail(employee.getContactData().getEmail());
        employeeParameters.setPosition(employee.getPosition().getPositionName());
        employeeParameters.setSalary(employee.getSalary());

        return employeeParameters;
    }

    public void createEmployeeData(EmployeeParameters employeeParameters) {
        Employee employee = new Employee();
        employee.setFirstName(employeeParameters.getFirstName());
        employee.setLastName(employeeParameters.getLastName());
        employee.setBirthdate(Date.valueOf(employeeParameters.getBirthDate()));
        employee.addAddress(createAddressData(employeeParameters));
        employee.setContactData(creatContactData(employeeParameters));
        employee.setPosition(createPositionData(employeeParameters));
        employee.setSalary(employeeParameters.getSalary());
        employeeRepository.save(employee);

    }

    private Address createAddressData(EmployeeParameters employeeParameters) {
        Address address = new Address();
        address.setStreetName(employeeParameters.getStreetName());
        address.setHouseNumber(employeeParameters.getHouseNumber());
        address.setHomeNumber(employeeParameters.getHomeNumber());
        address.setPostalCode(employeeParameters.getPostalCode());
        address.setCityName(employeeParameters.getCity());
        return address;
    }

    private Contact creatContactData(EmployeeParameters employeeParameters) {
        Contact contactData = new Contact();
        contactData.setEmail(employeeParameters.getEmail());
        contactData.setPhoneNumber(employeeParameters.getPhone());
        return contactData;
    }

    private Position createPositionData(EmployeeParameters employeeParameters) {
        Position positionObject = new Position();
        positionObject.setPositionName(employeeParameters.getPosition());
        return positionObject;
    }

    public void updateEmployeeData(EmployeeParameters employeeParameters) {
        Employee employee = getEmployeeById(employeeParameters.getId());
        employee.setFirstName(employeeParameters.getFirstName());
        employee.setLastName(employeeParameters.getLastName());
        employee.setBirthdate(Date.valueOf(employeeParameters.getBirthDate()));
        employee.addAddress(updateAddressData(employeeParameters));
        employee.setContactData(creatContactData(employeeParameters));
        employee.setPosition(createPositionData(employeeParameters));
        employee.setSalary(employeeParameters.getSalary());

        employeeRepository.save(employee);
    }

    private Address updateAddressData(EmployeeParameters employeeParameters) {
        Address address = addressRepository.findOne(employeeParameters.getId());
        address.setStreetName(employeeParameters.getStreetName());
        address.setHouseNumber(employeeParameters.getHouseNumber());
        address.setHomeNumber(employeeParameters.getHomeNumber());
        address.setPostalCode(employeeParameters.getPostalCode());
        address.setCityName(employeeParameters.getCity());
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
        report.setMinSalary(lowestSalaryEmployee.getSalary());
        report.setLowestPaidEmployeeName(lowestSalaryEmployee.getFirstName() + ' ' + lowestSalaryEmployee.getLastName());
        report.setLowestPaidEmployeePosition(lowestSalaryEmployee.getPosition().getPositionName());
        report.setMaxSalary(highestSalaryEmployee.getSalary());
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

    private void exportRawListToExcel(Iterable<Employee> employeeIterable) {

    }

}
