package webApp.repositories;

import org.springframework.data.jpa.repository.Query;
import webApp.entities.TaxDocument;
import webBase.repository.RepositoryBase;

import java.util.List;
import java.util.Map;

/**
 * Created by Syed Asher Ahmed on 5/10/2020.
 */
public interface TaxDocumentRepository extends RepositoryBase<TaxDocument, Long> {

    @Query(value = "SELECT users.`USER_NAME` userName , users.`USER_EMAIL` userEmail , taxDoc.TAX_DOCUMENT_CODE taxDocumentCode,\n" +
            "taxDoc.`USER_CODE` userCode , taxDoc.`DOCUMENT_NAME` documentName , taxDoc.`ABN_NUMBER` abnNumber , \n" +
            "taxDoc.`COMPANY_NAME` companyName , taxDoc.`BUSSINESS_CLIENT` bussinessClient , taxDoc.`CONTACT_NO` contactNo,\n" +
            "taxDoc.`POST_CODE` postCode , taxDoc.`BANK_NAME` bankName , taxDoc.`BSB_NUMBER` bsbNumber , taxDoc.`ACCOUNT_NO` accountNo ,\n" +
            "taxDoc.`SIGNATURE` signature , taxDoc.`SIGNATURE_CODE` signatureCode , taxDoc.`SIGNATURE_FONT` signatureFonts ,\n" +
            "taxDoc.`BUSSINESS_ADDRESS` bussinessAddress , taxDoc.`HOME_ADDRESS` homeAddress , taxDoc.`DOCUMENT_STATUS` documentStatus,\n" +
            "taxDoc.`APPROVED_BY` approvedBy , taxDoc.`IS_ACTIVE` isActive , \n" +
            "taxDoc.`CREATED_BY` createdBy , taxDoc.`CREATE_DATE` createdDate,\n" +
            "taxDoc.`MODIFIED_BY` modifiedBy , taxDoc.`MODIFIED_DATE` modifiedDate\n" +
            "FROM users users JOIN tax_document taxDoc ON(users.`USER_CODE` = taxDoc.`USER_CODE`) AND taxDoc.`DOCUMENT_STATUS` ='P'", nativeQuery = true)
    List<Map<String, Object>> getAllPendingTaxDocuments();

    @Query(value = "SELECT users.`USER_NAME` userName , users.`USER_EMAIL` userEmail , taxDoc.TAX_DOCUMENT_CODE taxDocumentCode,\n" +
            "taxDoc.`USER_CODE` userCode , taxDoc.`DOCUMENT_NAME` documentName , taxDoc.`ABN_NUMBER` abnNumber , \n" +
            "taxDoc.`COMPANY_NAME` companyName , taxDoc.`BUSSINESS_CLIENT` bussinessClient , taxDoc.`CONTACT_NO` contactNo,\n" +
            "taxDoc.`POST_CODE` postCode , taxDoc.`BANK_NAME` bankName , taxDoc.`BSB_NUMBER` bsbNumber , taxDoc.`ACCOUNT_NO` accountNo ,\n" +
            "taxDoc.`SIGNATURE` signature , taxDoc.`SIGNATURE_CODE` signatureCode , taxDoc.`SIGNATURE_FONT` signatureFonts ,\n" +
            "taxDoc.`BUSSINESS_ADDRESS` bussinessAddress , taxDoc.`HOME_ADDRESS` homeAddress , taxDoc.`DOCUMENT_STATUS` documentStatus,\n" +
            "taxDoc.`APPROVED_BY` approvedBy , taxDoc.`IS_ACTIVE` isActive , \n" +
            "taxDoc.`CREATED_BY` createdBy , taxDoc.`CREATE_DATE` createdDate,\n" +
            "taxDoc.`MODIFIED_BY` modifiedBy , taxDoc.`MODIFIED_DATE` modifiedDate\n" +
            "FROM users users JOIN tax_document taxDoc ON(users.`USER_CODE` = taxDoc.`USER_CODE`) AND taxDoc.`DOCUMENT_STATUS` ='A'", nativeQuery = true)
    List<Map<String, Object>> getAllApprovedTaxDocuments();

    @Query(value = "SELECT COUNT(*) FROM tax_document WHERE document_status='A'", nativeQuery = true)
    Long getAllApprovedTaxDocumentsCount();

    @Query(value = "SELECT COUNT(*) FROM tax_document WHERE document_status='P'", nativeQuery = true)
    Long getAllPendingTaxDocumentsCount();

}
