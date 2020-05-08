package webApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webApp.entities.dto.CustomResponseDto;
import webApp.entities.dto.LoginrequestDto;
import webApp.services.EmployeeService;
import webApp.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public CustomResponseDto LogInUser(@RequestBody LoginrequestDto loginRequest,
                                       @RequestParam(name = "userType") String userType ,
                                       HttpServletRequest request) {

        CustomResponseDto customResponseDto = null;
        if (userType.equals("user")) {
            customResponseDto = userService.userLogIn(loginRequest);
            request.getSession().setAttribute("USER_SESSION",customResponseDto.getOuthToken());
        } else if (userType.equals("emp")) {
            customResponseDto = employeeService.empLogIn(loginRequest);
            request.getSession().setAttribute("EMP_SESSION",customResponseDto.getOuthToken());
        }
        return customResponseDto;
    }
}
