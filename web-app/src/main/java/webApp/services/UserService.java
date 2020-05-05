package webApp.services;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.beans.factory.annotation.Autowired;
import webApp.entities.User;
import org.springframework.stereotype.Service;
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

    public User findByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    public User findByCode(Long userCode) {
        return userRepository.findByUserCode(userCode);
    }

    public User findByEmailAndPassword(LoginrequestDto loginrequestDto) {

        return userRepository.findByUserEmailAndUserPassword(loginrequestDto.getEmail(), loginrequestDto.getPassword());
    }

    public User findBySignatureCode(String signatureCode) {
        return userRepository.findByUserSignatureCode(signatureCode);
    }

    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setUserName(userRegistrationDto.getUserName());
        user.setUserEmail(userRegistrationDto.getUserEmail());
        user.setUserPassword(userRegistrationDto.getUserPassword());
        user.setUserContact(userRegistrationDto.getUserContact());
        user.setUserCnic(userRegistrationDto.getUserCnic());
        user.setUserDob(userRegistrationDto.getUserDob());
        user.setUserSignatureCode(UtilsClass.generateSignatureCode());
        user.setUserAddress(userRegistrationDto.getUserAddress());
        User existSignatureCode = findBySignatureCode(user.getUserSignatureCode());
        if (existSignatureCode != null) {
            user.setUserSignatureCode(UtilsClass.generateSignatureCode());
        }
        return userRepository.save(user);
    }


}
