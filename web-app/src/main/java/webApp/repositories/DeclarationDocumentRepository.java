package webApp.repositories;

import org.springframework.data.jpa.repository.Query;
import webApp.entities.DeclarationDocument;
import webBase.repository.RepositoryBase;

import java.util.List;
import java.util.Map;

/**
 * Created by Sarim on 5/7/2020.
 */
public interface DeclarationDocumentRepository extends RepositoryBase<DeclarationDocument, Long> {

    @Query(value = "SELECT users.`USER_NAME` userName , users.`USER_EMAIL` userEmail , document.DOCUMENT_CODE documentCode ,\n" +
            "document.`USER_CODE` userCode , document.`DOCUMENT_NAME` documentName, document.`ABN_NUMBER` abnNumber,\n" +
            "document.`BUSSINESS_CLIENT` bussinessClient ,document.`BSB_NUMBER` bsbNumber, document.`AGENT_NAME` agentName,\n" +
            "document.`DOCUMENT_PERIOD` documentPeriod , document.`PAPER_DECLARE_DATE` paperDeclareDate , document.`BUSSINESS_ADDRESS` bussinessAddress,\n" +
            "document.`DOCUMENT_STATUS` documentStatus, document.`APPROVED_BY` approvedBy, document.`SIGNATURE_CODE` signatureCode, \n" +
            "document.`SIGNATURE` signature , document.`SIGNATURE_FONT` signatureFonts , document.`IS_ACTIVE` isActive,\n" +
            "document.`CREATED_BY` createdBy , document.`CREATE_DATE` createdDate,\n" +
            "document.`MODIFIED_BY` modifiedBy , document.`MODIFIED_DATE` modifiedDate\n" +
            "FROM users users JOIN declaration_document document ON(users.`USER_CODE` = document.`USER_CODE`) AND \n" +
            "document.`DOCUMENT_STATUS` ='P' ", nativeQuery = true)
    List<Map<String, Object>> getAllPendingDeclarationDocuments();

    @Query(value = "SELECT users.`USER_NAME` userName , users.`USER_EMAIL` userEmail , document.DOCUMENT_CODE documentCode ,\n" +
            "document.`USER_CODE` userCode , document.`DOCUMENT_NAME` documentName, document.`ABN_NUMBER` abnNumber,\n" +
            "document.`BUSSINESS_CLIENT` bussinessClient ,document.`BSB_NUMBER` bsbNumber, document.`AGENT_NAME` agentName,\n" +
            "document.`DOCUMENT_PERIOD` documentPeriod , document.`PAPER_DECLARE_DATE` paperDeclareDate , document.`BUSSINESS_ADDRESS` bussinessAddress,\n" +
            "document.`DOCUMENT_STATUS` documentStatus, document.`APPROVED_BY` approvedBy, document.`SIGNATURE_CODE` signatureCode, \n" +
            "document.`SIGNATURE` signature , document.`SIGNATURE_FONT` signatureFonts , document.`IS_ACTIVE` isActive,\n" +
            "document.`CREATED_BY` createdBy , document.`CREATE_DATE` createdDate,\n" +
            "document.`MODIFIED_BY` modifiedBy , document.`MODIFIED_DATE` modifiedDate\n" +
            "FROM users users JOIN declaration_document document ON(users.`USER_CODE` = document.`USER_CODE`) AND \n" +
            "document.`DOCUMENT_STATUS` ='A'", nativeQuery = true)
    List<Map<String, Object>> getAllApprovedDeclarationDocuments();

    @Query(value = "SELECT COUNT(*) FROM declaration_document WHERE document_status='A'", nativeQuery = true)
    Long getAllApprovedDecDocumentsCount();

    @Query(value = "SELECT COUNT(*) FROM declaration_document WHERE document_status='P'", nativeQuery = true)
    Long getAllPendingDecDocumentsCount();

    @Query(value = "SELECT COUNT(*) FROM declaration_document WHERE document_status='A' AND USER_CODE=?1", nativeQuery = true)
    Long getAllApprovedUserDecDocumentsCount(String userCode);

    @Query(value = "SELECT COUNT(*) FROM declaration_document WHERE document_status='P' AND USER_CODE=?1", nativeQuery = true)
    Long getAllPendingUserDecDocumentsCount(String userCode);
}
