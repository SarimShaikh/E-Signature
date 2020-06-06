package webApp.repositories;
import org.hibernate.tool.hbm2ddl.TableMetadata;
import org.springframework.data.jpa.repository.Query;
import webApp.entities.RejectedDocument;
import webBase.repository.RepositoryBase;

import java.util.List;

/**
 * Created by Syed Asher Ahmed on 6/3/2020.
 */
public interface RejectedDocumentRepository extends RepositoryBase<RejectedDocument, Long> {

    @Query(value = "SELECT column_name\n" +
            "FROM information_schema.columns\n" +
            "WHERE table_name=?1 ",nativeQuery = true)
    List<String> findTableColumns(String table);
}
