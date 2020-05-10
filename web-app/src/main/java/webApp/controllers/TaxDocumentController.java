package webApp.controllers;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webApp.entities.TaxDocument;
import webApp.services.TaxDocumentService;
import webBase.controller.RestControllerBase;


/**
 * Created by Syed Asher Ahmed on 5/10/2020.
 */


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/taxDocument")
public class TaxDocumentController extends RestControllerBase<TaxDocument, Long> {

    public TaxDocumentController(TaxDocumentService service) {
        super(service);
    }
}
