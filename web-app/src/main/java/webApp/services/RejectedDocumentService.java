package webApp.services;

import org.hibernate.tool.hbm2ddl.TableMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webApp.entities.RejectedDocument;
import webApp.repositories.RejectedDocumentRepository;
import webBase.service.ServiceBase;

import java.util.ArrayList;
import java.util.List;

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

}
