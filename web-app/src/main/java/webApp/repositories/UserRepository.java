package webApp.repositories;

import webApp.entities.User;
import webBase.repository.RepositoryBase;

import java.util.Optional;

/**
 * Created by Sarim on 5/1/2020.
 */
public interface UserRepository extends RepositoryBase<User, Long> {

    User findByUserEmail(String email);
    User findByUserEmailAndUserPassword(String userName , String Password);
    User findByUserCode(Long userCode);
    User findByUserSignatureCode(String signatureCode);
}
