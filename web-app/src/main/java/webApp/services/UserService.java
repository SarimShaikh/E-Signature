package webApp.services;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.beans.factory.annotation.Autowired;
import webApp.entities.User;
import org.springframework.stereotype.Service;
import webApp.entities.dto.CustomResponseDto;
import webApp.entities.dto.LoginrequestDto;
import webApp.entities.dto.UserRegistrationDto;
import webApp.repositories.UserRepository;
import webApp.utils.UtilsClass;
import webBase.service.ServiceBase;

import java.util.Optional;

/**
 * Created by Sarim on 5/1/2020.
 */
@Service
public class UserService extends ServiceBase<User, Long> {


    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
        userRepository = repository;
    }

    public User findByCode(Long userCode) {
        return userRepository.findByUserCode(userCode);
    }

    public CustomResponseDto userLogIn(LoginrequestDto loginrequestDto) {
        CustomResponseDto customResponseDto = new CustomResponseDto();
        User user = userRepository.findByUserEmailAndUserPassword(loginrequestDto.getEmail(), loginrequestDto.getPassword());
        if (user != null) {
            customResponseDto.setResponseCode("200");
            customResponseDto.setStatus("Login");
            customResponseDto.setMessage("Login Successfully!");
            customResponseDto.setOuthToken(UtilsClass.generateOauthkey());
            customResponseDto.setEntityClass(user);
        } else {
            customResponseDto.setResponseCode("400");
            customResponseDto.setStatus("UnAuthorized");
            customResponseDto.setMessage("Invalid Email and Password!");
        }
        return customResponseDto;
    }

    public User findBySignatureCode(String signatureCode) {
        return userRepository.findByUserSignatureCode(signatureCode);
    }

    public CustomResponseDto save(UserRegistrationDto userRegistrationDto) {
        CustomResponseDto customResponseDto = new CustomResponseDto();
        User userExists = userRepository.findByUserEmail(userRegistrationDto.getUserEmail());
        if (userExists != null) {
            customResponseDto.setResponseCode("401");
            customResponseDto.setStatus("exists");
            customResponseDto.setMessage("User already registered with that email");
        } else {
            User user = new User();
            user.setUserName(userRegistrationDto.getUserName());
            user.setUserEmail(userRegistrationDto.getUserEmail());
            user.setUserPassword(userRegistrationDto.getUserPassword());
            user.setUserContact(userRegistrationDto.getUserContact());
            user.setUserCnic(userRegistrationDto.getUserCnic());
            user.setUserDob(UtilsClass.dateformat(userRegistrationDto.getUserDob()));
            user.setUserSignatureCode(UtilsClass.generateSignatureCode());
            user.setUserAddress(userRegistrationDto.getUserAddress());
            user.setIsSignSelect("N");
            User existSignatureCode = findBySignatureCode(user.getUserSignatureCode());
            if (existSignatureCode != null) {
                user.setUserSignatureCode(UtilsClass.generateSignatureCode());
            }
            userRepository.save(user);
            customResponseDto.setResponseCode("201");
            customResponseDto.setStatus("created");
            customResponseDto.setMessage("Registered Successfully!");
        }
        return customResponseDto;
    }


}
