package webApp.services;

import org.hibernate.tool.hbm2ddl.TableMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webApp.entities.RejectedDocument;
import webApp.repositories.RejectedDocumentRepository;
import webBase.service.ServiceBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Syed Asher Ahmed on 6/3/2020.
 */
@Service
public class RejectedDocumentService extends ServiceBase<RejectedDocument, Long> {

    private RejectedDocumentRepository rejectedDocumentRepository;

    @Autowired
    public RejectedDocumentService(RejectedDocumentRepository repository) {
        super(repository);
        rejectedDocumentRepository = repository;
    }

    public List<Map<String, Object>> getRejectedDocuments(){
        return rejectedDocumentRepository.getAllRejectedDocuments();
    }
}
