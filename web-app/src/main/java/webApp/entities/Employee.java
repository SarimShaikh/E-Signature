package webApp.entities;

import webApp.entities.audit.EntityBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Sarim on 5/1/2020.
 */

@Entity
@Table(name = "employee")
public class Employee extends EntityBase<String> implements Serializable {

    private Long employeeCode;
    private String employeeName;
    private String employeeEmail;
    private String employeePassword;
    private String employeeContact;
    private Byte isActive;

    @Id
    @Column(name = "EMP_CODE", nullable = false)
    public Long getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Long employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Basic
    @Column(name = "EMP_NAME", nullable = false)
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Basic
    @Column(name = "EMP_EMAIL", nullable = false)
    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    @Basic
    @Column(name = "EMP_PASSWORD", nullable = false)
    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    @Basic
    @Column(name = "EMP_CONTACT", nullable = false)
    public String getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(String employeeContact) {
        this.employeeContact = employeeContact;
    }

    @Basic
    @Column(name = "IS_ACTIVE", nullable = false)
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

}
