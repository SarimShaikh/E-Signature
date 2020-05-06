package webApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webApp.entities.Employee;
import webApp.entities.dto.CustomResponseDto;
import webApp.entities.dto.LoginrequestDto;
import webApp.services.EmployeeService;
import webBase.controller.RestControllerBase;

import javax.validation.Valid;

/**
 * Created by Sarim on 5/5/2020.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/employee")
public class EmployeeController extends RestControllerBase<Employee, Long> {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService service) {
        super(service);
        employeeService = service;
    }


    @PostMapping("/getemployee")
    @ResponseBody
    public Employee getByEmployeeCode(@RequestParam(name = "empCode") Long empCode) {
        return employeeService.getEmployeeByCode(empCode);
    }

    @PostMapping("/signup")
    public CustomResponseDto registerUser(@Valid @RequestBody Employee employee) throws Exception {
        return employeeService.saveEmployee(employee);
    }
}
