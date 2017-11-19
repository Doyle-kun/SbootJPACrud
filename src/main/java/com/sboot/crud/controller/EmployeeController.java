package com.sboot.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createEmployee(@ModelAttribute(name = "employeeParams") EmployeeParameters employeeParameters, Model model) {
        if (employeeParameters.getId() == null) {
            employeeService.createEmployeeData(employeeParameters);
        } else {
            employeeService.updateEmployeeData(employeeParameters);
        }
        return employeeList(model);
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    String addEmployee(Model model) {
        model.addAttribute("employeeParams", new EmployeeParameters());
        return "add";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    String editEmployee(Model model, @PathVariable(value = "id") String Id) {
        model.addAttribute("employee", employeeService.getEmployeeById(Long.valueOf(Id)));
        model.addAttribute("employeeParams", employeeService.setEmployeeParamsForEdit(Long.valueOf(Id)));
        return "edit";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST) // should be DELETE, but I couldn't handle it from frontend
    public String deleteEmployee(@RequestParam Long Id, Model model) {
        employeeService.deleteEmployee(Id);
        return employeeList(model);
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String createReport(Model model) {
        model.addAttribute("report", employeeService.createReport());
        return "report";
    }

}