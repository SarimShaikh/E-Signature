package webApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webApp.entities.TaxDocument;
import webApp.entities.dto.DocumentDto;
import webApp.services.TaxDocumentService;
import webBase.controller.RestControllerBase;

import java.util.List;


/**
 * Created by Syed Asher Ahmed on 5/10/2020.
 */


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/taxDocument")
public class TaxDocumentController extends RestControllerBase<TaxDocument, Long> {

    private TaxDocumentService taxDocumentService;

    @Autowired
    public TaxDocumentController(TaxDocumentService service) {
        super(service);
        taxDocumentService = service;
    }

    @PostMapping("/getAllPendingTaxdocuments")
    @ResponseBody
    public List<DocumentDto> getAllPendingDocuments() {
        return taxDocumentService.getAllPendingTaxation();
    }

    @PostMapping("/getAllApprovedTaxdocuments")
    @ResponseBody
    public List<DocumentDto> getAllApprovedDocuments() {
        return taxDocumentService.getAllApprovedTaxation();
    }
}
