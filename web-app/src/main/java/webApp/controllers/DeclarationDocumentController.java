package webApp.controllers;

import webApp.entities.DeclarationDocument;
import webApp.services.DeclarationDocumentService;
import webBase.controller.RestControllerBase;

/**
 * Created by Sarim on 5/7/2020.
 */
public class DeclarationDocumentController extends RestControllerBase<DeclarationDocument, Long> {

    public DeclarationDocumentController(DeclarationDocumentService service) {
        super(service);
    }
}
