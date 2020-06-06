package webApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webApp.entities.DeclarationDocument;
import webApp.entities.dto.DocumentDto;
import webApp.services.DeclarationDocumentService;
import webBase.controller.RestControllerBase;

import java.util.List;

/**
 * Created by Sarim on 5/7/2020.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/declaration")
public class DeclarationDocumentController extends RestControllerBase<DeclarationDocument, Long> {

    private DeclarationDocumentService declarationDocumentService;

    @Autowired
    public DeclarationDocumentController(DeclarationDocumentService service) {
        super(service);
        declarationDocumentService = service;
    }

    @PostMapping("/getAllPendingDecdocuments")
    @ResponseBody
    public List<DocumentDto> getAllPendingDocuments() {
        return declarationDocumentService.getAllpendingDeclaration();
    }

    @PostMapping("/getAllApprovedDecdocuments")
    @ResponseBody
    public List<DocumentDto> getAllApprovedDocuments() {
        return declarationDocumentService.getAllApprovedDeclaration();
    }

    @PostMapping("/getAllApprovedDecdocumentsCount")
    @ResponseBody
    public long getAllApprovedDocumentsCount() {
        return declarationDocumentService.getApprovedDecDocCount();
    }

    @PostMapping("/getAllPendingDecdocumentsCount")
    @ResponseBody
    public long getAllPendingDocumentsCount() {
        return declarationDocumentService.getPendingDecDocCount();
    }
}
