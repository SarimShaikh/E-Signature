package webApp.services;

import org.springframework.stereotype.Service;
import webApp.entities.DeclarationDocument;
import webApp.repositories.DeclarationDocumentRepository;
import webBase.service.ServiceBase;

/**
 * Created by Sarim on 5/7/2020.
 */
@Service
public class DeclarationDocumentService extends ServiceBase<DeclarationDocument,Long> {

    public DeclarationDocumentService(DeclarationDocumentRepository repository) {
        super(repository);
    }
}
