package webApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import webApp.entities.DeclarationDocument;
import webApp.entities.TaxDocument;
import webApp.entities.User;
import org.springframework.stereotype.Service;
import webApp.entities.dto.CustomResponseDto;
import webApp.entities.dto.LoginrequestDto;
import webApp.entities.dto.UserRegistrationDto;
import webApp.repositories.UserRepository;
import webApp.utils.UtilsClass;
import webBase.service.ServiceBase;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Created by Sarim on 5/1/2020.
 */
@Service
public class UserService extends ServiceBase<User, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
        userRepository = repository;
    }

    public User findByCode(Long userCode) {
        return userRepository.findByUserCode(userCode);
    }

    public User findPendingUserDocuments(Long userCode) {
        User user = userRepository.findByUserCode(userCode);
        List<DeclarationDocument> decList = user.getDeclarationDocuments().stream().collect(Collectors.toList());
        List<TaxDocument> taxList = user.getTaxDocuments().stream().collect(Collectors.toList());

        decList = decList.stream()
                .filter(p -> p.getDocumentStatus().equals("P")).collect(Collectors.toList());

        taxList = taxList.stream()
                .filter(p -> p.getDocumentStatus().equals("P")).collect(Collectors.toList());

        user.setDeclarationDocuments(decList);
        user.setTaxDocuments(taxList);
        return user;
    }

    public User findApprovedUserDocuments(Long userCode) {
        User user = userRepository.findByUserCode(userCode);
        List<DeclarationDocument> decList = user.getDeclarationDocuments().stream().collect(Collectors.toList());
        List<TaxDocument> taxList = user.getTaxDocuments().stream().collect(Collectors.toList());

        decList = decList.stream()
                .filter(p -> p.getDocumentStatus().equals("A")).collect(Collectors.toList());

        taxList = taxList.stream()
                .filter(p -> p.getDocumentStatus().equals("A")).collect(Collectors.toList());

        user.setDeclarationDocuments(decList);
        user.setTaxDocuments(taxList);
        return user;
    }

    @Transactional
    public User assignSignature(User user) {
        return entityManager.merge(user);
    }

    public CustomResponseDto userLogIn(LoginrequestDto loginrequestDto) {
        CustomResponseDto customResponseDto = new CustomResponseDto();
        List<Object[]> userList = userRepository.findByUserEmailAndUserPassword(loginrequestDto.getEmail(), loginrequestDto.getPassword());
        User user = new User();
        if (userList.size() == 1) {
            Object[] userDetails = userList.get(0);
            user.setUserCode((Long) userDetails[0]);
            user.setUserName(String.valueOf(userDetails[1]));
            user.setUserEmail(String.valueOf(userDetails[2]));
            user.setUserSignatureCode(String.valueOf(userDetails[3]));
            user.setUserSignature(String.valueOf(userDetails[4]));
            user.setIsSignSelect(String.valueOf(userDetails[5]));

            if (user != null) {
                customResponseDto.setResponseCode("200");
                customResponseDto.setStatus("Login");
                customResponseDto.setMessage("Login Successfully!");
                customResponseDto.setOuthToken(UtilsClass.generateOauthkey());
                customResponseDto.setEntityClass(user);
            }
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
