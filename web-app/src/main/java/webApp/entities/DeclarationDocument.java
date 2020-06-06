package webApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import webApp.entities.audit.DocumentListener;
import webApp.entities.audit.EntityBase;
import webApp.utils.UtilsClass;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sarim on 5/7/2020.
 */
@Entity
@Table(name = "declaration_document")
@EntityListeners({AuditingEntityListener.class, DocumentListener.class})
public class DeclarationDocument extends EntityBase<String> implements Serializable {

    private Long documentCode;
    private Long userCode;
    private String documentName;
    private String abnNumber;
    private String bussinessClient;
    private String bsbNumber;
    private String agentName;
    private String documentPeriod;
    private String paperDeclareDate;
    private String bussinessAddress;
    private String documentStatus;
    private String approvedBy;
    private String signatureCode;
    private String signature;
    private String signatureFonts;
    private Byte isActive;
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
    @Column(name = "AGENT_NAME", nullable = false)
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    @Basic
    @Column(name = "DOCUMENT_PERIOD", nullable = false)
    public String getDocumentPeriod() {
        return documentPeriod;
    }

    public void setDocumentPeriod(String documentPeriod) {
        this.documentPeriod = documentPeriod;
    }

    @Basic
    @Column(name = "PAPER_DECLARE_DATE", nullable = false)
    public String getPaperDeclareDate() {
        return paperDeclareDate;
    }

    public void setPaperDeclareDate(String paperDeclareDate) {
        this.paperDeclareDate = paperDeclareDate;
    }

    @Basic
    @Column(name = "BUSSINESS_ADDRESS", nullable = false)
    public String getBussinessAddress() {
        return bussinessAddress;
    }

    public void setBussinessAddress(String bussinessAddress) {
        this.bussinessAddress = bussinessAddress;
    }

    @Basic
    @Column(name = "DOCUMENT_STATUS", nullable = false)
    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    @Basic
    @Column(name = "APPROVED_BY", nullable = false)
    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
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
    @Column(name = "SIGNATURE_FONT", nullable = false)
    public String getSignatureFonts() {
        return signatureFonts;
    }

    public void setSignatureFonts(String signatureFonts) {
        this.signatureFonts = signatureFonts;
    }

    @Basic
    @Column(name = "IS_ACTIVE", nullable = false)
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
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
