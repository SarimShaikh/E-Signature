package webApp.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webApp.entities.DeclarationDocument;
import webApp.services.DeclarationDocumentService;
import webBase.controller.RestControllerBase;

/**
 * Created by Sarim on 5/7/2020.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/declaration")
public class DeclarationDocumentController extends RestControllerBase<DeclarationDocument, Long> {

    public DeclarationDocumentController(DeclarationDocumentService service) {
        super(service);
    }
}
