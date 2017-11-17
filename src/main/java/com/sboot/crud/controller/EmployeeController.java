package com.sboot.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sboot.crud.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String employeeList(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployeesIterable());
        return "index";
    }

    @RequestMapping(path = "/employee/edit/{id}", method = RequestMethod.GET)
    String editEmployee(Model model, @PathVariable(value = "id") String Id) {
        model.addAttribute("employee", employeeService.getEmployeeById(Long.valueOf(Id)));
        return "edit";
    }

    @RequestMapping(path = "/employee/add", method = RequestMethod.GET)
    String add() {
        return "add";
    }

    /*
     * Honestly I know that i looks terrible, usually I pack parameters into object, but this week I have no idea and time to learn how to do it on
     * frontend
     */
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    @ResponseBody
    public String createEmployee(@RequestParam(value = "id", defaultValue = "") Long id, @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "birthDate") String birthDate, @RequestParam(value = "streetName") String streetName,
            @RequestParam(value = "houseNumber") String houseNumber, @RequestParam(value = "houseNumber") String homeNumber,
            @RequestParam(value = "postalCode") String postalCode, @RequestParam(value = "city") String city, @RequestParam(value = "phone") String phone,
            @RequestParam(value = "email") String email, @RequestParam(value = "position") String position, @RequestParam(value = "salary") Double salary) {
        if (id == null) {
            employeeService.createEmployeeData(firstName, lastName, birthDate, streetName, houseNumber, homeNumber, postalCode, city, phone, email, position,
                    salary);
        } else {
            employeeService.updateEmployeeData(id, firstName, lastName, birthDate, streetName, houseNumber, homeNumber, postalCode, city, phone, email,
                    position,
                    salary);
        }
        return "index";
    }

    @RequestMapping(value = "/employee/delete", method = RequestMethod.POST) // should be DELETE, but I couldn't handle it from frontend
    @ResponseBody
    public String deleteEmployee(@RequestParam Long Id) {
        employeeService.deleteEmployee(Id);
        return "index";
    }

    @RequestMapping(value = "/report/print", method = RequestMethod.GET)
    public String createReport(Model model) {
        model.addAttribute("report", employeeService.createReport());
        return "report";
    }
}