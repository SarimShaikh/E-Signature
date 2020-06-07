package webApp.repositories;
import org.hibernate.tool.hbm2ddl.TableMetadata;
import org.springframework.data.jpa.repository.Query;
import webApp.entities.RejectedDocument;
import webBase.repository.RepositoryBase;

import java.util.List;
import java.util.Map;

/**
 * Created by Syed Asher Ahmed on 6/3/2020.
 */
public interface RejectedDocumentRepository extends RepositoryBase<RejectedDocument, Long> {

    @Query(value = "SELECT column_name\n" +
            "FROM information_schema.columns\n" +
            "WHERE table_name=?1 ",nativeQuery = true)
    List<String> findTableColumns(String table);

    @Query(value = "SELECT users.`USER_EMAIL` userEmail , users.`USER_NAME` userName, reject.`DOCUMENT_CODE` documentCode,\n" +
            "reject.`DOCUMENT_NAME` documentName , reject.`ABN_NUMBER` abnNumber,\n" +
            "reject.`BUSSINESS_CLIENT` bussinessClient , reject.`BSB_NUMBER` bsbNumber , reject.`BANK_NAME` bankName ,\n" +
            "reject.`ACCOUNT_NO`accountNo , reject.`SIGNATURE` signature , reject.`SIGNATURE_FONT` signatureFont, reject.`REJECT_BY` rejectBy,\n" +
            "reject.`DOCUMENT_TYPE` documentType\n" +
            "FROM users users JOIN reject_document_log reject ON(users.`USER_CODE` = reject.`USER_CODE`)" , nativeQuery = true)
    List<Map<String, Object>> getAllRejectedDocuments();
}
