package webApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import webApp.entities.audit.EntityBase;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Syed Asher Ahmed on 6/3/2020.
 */
@Entity
@Table(name = "reject_document_log")
@EntityListeners(AuditingEntityListener.class)
public class RejectedDocument extends EntityBase<String> implements Serializable {


    private Long documentCode;
    private Long userCode;
    private String documentName;
    private String abnNumber;
    private String bussinessClient;
    private String bsbNumber;
    private String signatureCode;
    private String signature;
    private String bankName;
    private String accountNo;
    private String rejectBy;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOCUMENT_CODE", nullable = false)
    public Long getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(Long documentCode) {
        this.documentCode = documentCode;
    }

    @Basic
    @Column(name = "USER_CODE", nullable = false)
    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

    @Basic
    @Column(name = "DOCUMENT_NAME", nullable = false)
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @Basic
    @Column(name = "ABN_NUMBER", nullable = false)
    public String getAbnNumber() {
        return abnNumber;
    }

    public void setAbnNumber(String abnNumber) {
        this.abnNumber = abnNumber;
    }

    @Basic
    @Column(name = "BUSSINESS_CLIENT", nullable = false)
    public String getBussinessClient() {
        return bussinessClient;
    }

    public void setBussinessClient(String bussinessClient) {
        this.bussinessClient = bussinessClient;
    }

    @Basic
    @Column(name = "BSB_NUMBER", nullable = false)
    public String getBsbNumber() {
        return bsbNumber;
    }

    public void setBsbNumber(String bsbNumber) {
        this.bsbNumber = bsbNumber;
    }

    @Basic
    @Column(name = "SIGNATURE_CODE", nullable = false)
    public String getSignatureCode() {
        return signatureCode;
    }

    public void setSignatureCode(String signatureCode) {
        this.signatureCode = signatureCode;
    }

    @Basic
    @Column(name = "SIGNATURE", nullable = false)
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Basic
    @Column(name = "BANK_NAME", nullable = false)
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Basic
    @Column(name = "ACCOUNT_NO", nullable = false)
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Basic
    @Column(name = "REJECT_BY", nullable = false)
    public String getRejectBy() {
        return rejectBy;
    }

    public void setRejectBy(String rejectBy) {
        this.rejectBy = rejectBy;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_CODE", nullable = false , insertable = false , updatable = false)
    @JsonBackReference
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
