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

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String userLogin(HttpServletRequest request) {
        if (request.getSession().getAttribute("USER_SESSION") != null) {
            return "pannel/user-dashboard";
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String userLogOut(HttpServletRequest request) {
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/user/signup"}, method = RequestMethod.GET)
    public String userRegistration(Model model) {
        return "login-signup/user-registration";
    }


    @RequestMapping(value = {"/user/dashboard"}, method = RequestMethod.GET)
    public String userdashboard(HttpServletRequest request) {
        if (request.getSession().getAttribute("USER_SESSION") != null) {
            return "pannel/user-dashboard";
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/user/profile"}, method = RequestMethod.GET)
    public String userProfile(HttpServletRequest request) {
        if (request.getSession().getAttribute("USER_SESSION") != null) {
            return "pannel/user-profile";
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/user/declaration"}, method = RequestMethod.GET)
    public String declarationForm(HttpServletRequest request) {
        if (request.getSession().getAttribute("USER_SESSION") != null) {
            return "pannel/declaration-form";
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/user/taxDocument"}, method = RequestMethod.GET)
    public String taxDocumentForm(HttpServletRequest request) {
        if (request.getSession().getAttribute("USER_SESSION") != null) {
            return "pannel/tax-document-form";
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/user/pendingDocuments"}, method = RequestMethod.GET)
    public String pendingDocumentForm(HttpServletRequest request) {
        if (request.getSession().getAttribute("USER_SESSION") != null) {
            return "pannel/pending-form";
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/employee/signup"}, method = RequestMethod.GET)
    public String employeeRegistration(Model model) {
        return "login-signup/employee-registration";
    }


    @RequestMapping(value = {"/employee/dashboard"}, method = RequestMethod.GET)
    public String empdashboard(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMP_SESSION") != null) {
            return "pannel/emp-dashboard";
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/employee/profile"}, method = RequestMethod.GET)
    public String empProfile(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMP_SESSION") != null) {
            return "pannel/emp-profile";
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/employee/pendingDocuments"}, method = RequestMethod.GET)
    public String EmppendingDocumentForm(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMP_SESSION") != null) {
            return "pannel/emp-pending-form";
        }
        return "login-signup/login";
    }

    @RequestMapping(value = {"/employee/approvedDocuments"}, method = RequestMethod.GET)
    public String EmpApprovedDocumentForm(HttpServletRequest request) {
        if (request.getSession().getAttribute("EMP_SESSION") != null) {
            return "pannel/emp-approved-form";
        }
        return "login-signup/login";
    }
}
