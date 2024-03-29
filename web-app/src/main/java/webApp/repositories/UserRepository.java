package webApp.repositories;

import org.springframework.data.jpa.repository.Query;
import webApp.entities.User;
import webBase.repository.RepositoryBase;

import java.util.List;


/**
 * Created by Sarim on 5/1/2020.
 */
public interface UserRepository extends RepositoryBase<User, Long> {


    User findByUserEmailAndUserPassword(String userEmail, String userPassword);

    User findByUserEmail(String email);

    User findByUserCode(Long userCode);

    User findByUserSignatureCode(String signatureCode);
}
