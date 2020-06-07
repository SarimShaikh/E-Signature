package webApp.entities.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.util.*;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

/**
 * Created by Sarim on 6/4/2020.
 */
@Component
public class DocumentListener {

    static Logger LOGGER = Logger.getLogger(DocumentListener.class.getName());
    private static JdbcTemplate jdbcTemplate;
    @Autowired
    private HttpSession session;

    @Autowired
    @Qualifier("jdbcTemplate")
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostPersist
    public void postPersist(Object target) {
        //perform(target);
    }

    @PostUpdate
    public void postUpdate(Object target) {
        //updateTable(target);
    }

    @PostRemove
    public void postRemove(Object target) {
        deleteDocumentData(target);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteDocumentData(Object target) {

        if (target.getClass().isAnnotationPresent(Table.class)) {

            Map<String, String> entityMap = new LinkedHashMap<>();
            Map<String, String> auditMap = new LinkedHashMap<>();
            Map<String, String> queryParam = getTableCols("reject_document_log");

            String tableName = target.getClass().getDeclaredAnnotation(Table.class).name().toString();
            StringBuilder sb_insert = new StringBuilder();
            StringBuilder sb_values = new StringBuilder();

            BeanInfo entity = null;
            try {
                entity = Introspector.getBeanInfo(target.getClass(), Object.class);
                PropertyDescriptor[] properties = entity.getPropertyDescriptors();
                for (PropertyDescriptor property : properties) {

                    if (property.getReadMethod().isAnnotationPresent(Column.class) && property.getReadMethod().invoke(target) != null) {
                        entityMap.put(property.getReadMethod().getAnnotation(Column.class).name(), property.getReadMethod().invoke(target).toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            auditMap.put("REJECT_BY", session.getAttribute("EMP_NAME").toString());
            auditMap.put("CREATED_BY", session.getAttribute("EMP_NAME").toString());
            auditMap.put("CREATE_DATE", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            auditMap.put("MODIFIED_BY", session.getAttribute("EMP_NAME").toString());
            auditMap.put("MODIFIED_DATE", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            for (Map.Entry<String, String> entityCol : entityMap.entrySet()) {
                if (queryParam.containsKey(entityCol.getKey())) {
                    queryParam.put(entityCol.getKey(), entityCol.getValue());
                }
            }

            for (Map.Entry<String, String> audit : auditMap.entrySet()) {
                if (queryParam.containsKey(audit.getKey())) {
                    queryParam.put(audit.getKey(), audit.getValue());
                }
            }

            if (tableName.equals("declaration_document")) {
                queryParam.put("DOCUMENT_TYPE", "DEC");
                queryParam.remove("REJ_DOC_ID");
                queryParam.remove("BANK_NAME");
                queryParam.remove("ACCOUNT_NO");
            } else if (tableName.equals("tax_document")) {
                queryParam.put("DOCUMENT_TYPE", "TAX");
                queryParam.remove("REJ_DOC_ID");
            }

            sb_insert.append("INSERT INTO reject_document_log").append(" (");
            sb_values.append("VALUES (");

            for (Map.Entry<String, String> params : queryParam.entrySet()) {
                sb_insert.append(params.getKey()).append(",");
                sb_values.append("?").append(" ,");
            }

            if (sb_insert.toString().endsWith(",")) {
                sb_insert.delete(sb_insert.length() - 1, sb_insert.length());
                sb_values.delete(sb_values.length() - 1, sb_values.length());
            }

            sb_insert.append(") ").append(sb_values).append(")");
            LOGGER.info("Query------>" + sb_insert.toString());

            try {

                jdbcTemplate.execute(sb_insert.toString(), new PreparedStatementCallback<Boolean>() {
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement ps)
                            throws SQLException, DataAccessException {
                        int counter = 1;
                        for (Map.Entry<String, String> params : queryParam.entrySet()) {
                            ps.setString(counter, params.getValue());
                            counter++;
                            LOGGER.info("counter-->" + counter);
                        }
                        return ps.execute();
                    }
                });

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public Map<String, String> getTableCols(String tableName) {
        String sql = "SELECT column_name FROM information_schema.columns WHERE table_name='" + tableName + "' ";
        Map<String, String> columns = new LinkedHashMap<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            columns.put((String) row.get("column_name"), null);
        }
        return columns;
    }

}
