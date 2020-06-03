package webApp.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webApp.entities.RejectedDocument;
import webApp.services.RejectedDocumentService;
import webBase.controller.RestControllerBase;

/**
 * Created by Syed Asher Ahmed on 6/3/2020.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/rejectedDocuments")
public class RejectedDocumentController extends RestControllerBase<RejectedDocument, Long> {

    private RejectedDocumentService rejectedDocumentService;

    @Autowired
    public RejectedDocumentController(RejectedDocumentService service) {
        super(service);
        rejectedDocumentService = service;
    }

}
