package webApp.services;

import org.springframework.stereotype.Service;
import webApp.entities.TaxDocument;
import webApp.repositories.TaxDocumentRepository;
import webBase.service.ServiceBase;


/**
 * Created by Syed Asher Ahmed on 5/10/2020.
 */

@Service
public class TaxDocumentService extends ServiceBase<TaxDocument, Long> {
    public TaxDocumentService(TaxDocumentRepository repository) {
        super(repository);
    }
}
