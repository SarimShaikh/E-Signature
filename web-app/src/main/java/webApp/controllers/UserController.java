package webApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webApp.entities.dto.CustomResponseDto;
import webApp.entities.dto.LoginrequestDto;
import webApp.entities.dto.UserRegistrationDto;
import webBase.controller.RestControllerBase;
import webApp.entities.User;
import webApp.services.UserService;

import javax.validation.Valid;

/**
 * Created by Sarim on 5/1/2020.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/users")
public class UserController extends RestControllerBase<User, Long> {


    private UserService userService;

    @Autowired
    public UserController(UserService service) {
        super(service);
        userService = service;
    }

    @PostMapping("/getuser")
    @ResponseBody
    public User getByCode(@RequestParam(name = "userCode") Long userCode) {
        return userService.findByCode(userCode);
    }

    @PostMapping("/getuserPendingdocuments")
    @ResponseBody
    public User getPendingDocumentsByUserCode(@RequestParam(name = "userCode") Long userCode) {
        return userService.findPendingUserDocuments(userCode);
    }

    @PostMapping("/getuserApproveddocuments")
    @ResponseBody
    public User getApprovedDocumentsByUserCode(@RequestParam(name = "userCode") Long userCode) {
        return userService.findApprovedUserDocuments(userCode);
    }

    @PostMapping("/signup")
    public CustomResponseDto registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) throws Exception {
        return userService.save(userRegistrationDto);
    }

    @PostMapping("/assignSignature")
    public User assignSignature(@Valid @RequestBody User user) throws Exception {
        return userService.assignSignature(user);
    }
}
