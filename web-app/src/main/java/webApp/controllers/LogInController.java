package webApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webApp.entities.dto.CustomResponseDto;
import webApp.entities.dto.LoginrequestDto;
import webApp.services.EmployeeService;
import webApp.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

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

    @PostMapping("/user/validate")
    public Map userValidate(@RequestParam(name = "outhToken") String outhToken, HttpServletRequest request) {
        String userSession = request.getSession().getAttribute("USER_SESSION").toString();
        if (userSession != null && userSession.equals(outhToken)) {
            return Collections.singletonMap("path", "/user/dashboard");
        }
        return Collections.singletonMap("path", "/login");
    }
    @PostMapping("/employee/validate")
    public Map empValidate(@RequestParam(name = "outhToken") String outhToken, HttpServletRequest request) {
        String empSession = request.getSession().getAttribute("EMP_SESSION").toString();
        if (empSession != null && empSession.equals(outhToken)) {
            return Collections.singletonMap("path", "/employee/dashboard");
        }
        return Collections.singletonMap("path", "/login");
    }
}
