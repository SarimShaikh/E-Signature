package webApp.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import webApp.entities.audit.EntityBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sarim on 5/1/2020.
 */

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends EntityBase<String> implements Serializable {

    private Long userCode;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userContact;
    private String userCnic;
    private String userDob;
    private String userSignatureCode;
    private String userSignature;
    private String userAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_CODE", nullable = false)
    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "USER_EMAIL", nullable = false)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Basic
    @Column(name = "USER_PASSWORD", nullable = false)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Basic
    @Column(name = "USER_CONTACT", nullable = false)
    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    @Basic
    @Column(name = "USER_CNIC", nullable = false)
    public String getUserCnic() {
        return userCnic;
    }

    public void setUserCnic(String userCnic) {
        this.userCnic = userCnic;
    }

    @Basic
    @Column(name = "USER_DOB", nullable = false)
    public String getUserDob() {
        return userDob;
    }

    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }

    @Basic
    @Column(name = "USER_SIGNATURE_CODE", unique = true, nullable = false)
    public String getUserSignatureCode() {
        return userSignatureCode;
    }

    public void setUserSignatureCode(String userSignatureCode) {
        this.userSignatureCode = userSignatureCode;
    }

    @Basic
    @Column(name = "USER_SIGNATURE", nullable = false)
    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    @Basic
    @Column(name = "USER_ADDRESS", nullable = false)
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }


}
