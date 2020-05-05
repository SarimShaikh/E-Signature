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

    @PostMapping("/login")
    public CustomResponseDto LogInUser(@Valid @RequestBody LoginrequestDto loginRequest) {
        User user = userService.findByEmailAndPassword(loginRequest);
        CustomResponseDto customResponseDto = new CustomResponseDto();
        if(user!=null){
            customResponseDto.setResponseCode("200");
            customResponseDto.setStatus("Login");
            customResponseDto.setMessage("Login Successfully!");
            customResponseDto.setEntityClass(user);
        }else{
            customResponseDto.setResponseCode("400");
            customResponseDto.setStatus("UnAuthorized");
            customResponseDto.setMessage("Invalid Email and Password!");
        }
        return customResponseDto;
    }

    @PostMapping("/getuser")
    @ResponseBody
    public User getByCode(@RequestParam(name = "userCode") Long userCode) {
        return userService.findByCode(userCode);
    }

    @PostMapping("/signup")
    public CustomResponseDto registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto) throws Exception {
        CustomResponseDto customResponseDto = new CustomResponseDto();
        User user = userService.findByEmail(userRegistrationDto.getUserEmail());
        if (user != null) {
            customResponseDto.setResponseCode("401");
            customResponseDto.setStatus("exists");
            customResponseDto.setMessage("User already registered with that email");
        } else {
            userService.save(userRegistrationDto);
            customResponseDto.setResponseCode("201");
            customResponseDto.setStatus("created");
            customResponseDto.setMessage("Registered Successfully!");
        }

        return customResponseDto;
    }
}
