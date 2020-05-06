package webApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webApp.entities.dto.CustomResponseDto;
import webApp.entities.dto.LoginrequestDto;
import webApp.services.EmployeeService;
import webApp.services.UserService;

/**
 * Created by Sarim on 5/6/2020.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LogInController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public CustomResponseDto LogInUser(@RequestBody LoginrequestDto loginRequest, @RequestParam(name = "userType") String userType) {
        CustomResponseDto customResponseDto = null;

        if (userType.equals("user")) {
            customResponseDto = userService.userLogIn(loginRequest);
        } else if (userType.equals("emp")) {
            customResponseDto = employeeService.empLogIn(loginRequest);
        }
        return customResponseDto;
    }
}
