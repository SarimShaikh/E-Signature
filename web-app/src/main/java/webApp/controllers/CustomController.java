package webApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sarim on 5/5/2020.
 */
@Controller
public class CustomController {

    @RequestMapping(value = {"/user/login"}, method = RequestMethod.GET)
    public String userLogin(Model model) {
        return "login-signup/login";
    }

    @RequestMapping(value = {"/user/signup"}, method = RequestMethod.GET)
    public String userRegistration(Model model) {
        return "login-signup/user-registration";
    }

    @RequestMapping(value = {"/user/dashboard"}, method = RequestMethod.GET)
    public String userdashboard(Model model, @RequestParam(name = "outhToken") String outhToken, HttpServletRequest request) {
        String userSession = request.getSession().getAttribute("USER_SESSION").toString();
        if (userSession!=null && userSession.equals(outhToken)) {
            return "pannel/user-dashboard";
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/user/profile"}, method = RequestMethod.GET)
    public String userProfile(Model model) {
        return "pannel/user-profile";
    }

    @RequestMapping(value = {"/employee/login"}, method = RequestMethod.GET)
    public String employeeLogin(Model model) {
        return "login-signup/login";
    }

    @RequestMapping(value = {"/employee/signup"}, method = RequestMethod.GET)
    public String employeeRegistration(Model model) {
        return "login-signup/employee-registration";
    }

    @RequestMapping(value = {"/employee/dashboard"}, method = RequestMethod.GET)
    public String empdashboard(Model model,@RequestParam(name = "outhToken") String outhToken, HttpServletRequest request) {
        String empSession = request.getSession().getAttribute("EMP_SESSION").toString();
        if(empSession!=null && empSession.equals(outhToken)){
            return "pannel/emp-dashboard";
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/employee/profile"}, method = RequestMethod.GET)
    public String empProfile(Model model) {
        return "pannel/emp-profile";
    }
}
