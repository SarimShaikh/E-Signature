package webApp.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webApp.entities.RejectedDocument;
import webApp.services.RejectedDocumentService;
import webBase.controller.RestControllerBase;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/getAllRejectDocuments")
    @ResponseBody
    public List<Map<String, Object>> getAllRejecteDocuments() {
        return rejectedDocumentService.getRejectedDocuments();
    }

    @GetMapping("/getAllRejectDecDocuments")
    @ResponseBody
    public List<Map<String, Object>> getAllRejectedDecDocuments() {
        return rejectedDocumentService.getRejectedDecDocuments();
    }

    @PostMapping("/getAllRejectUserDecdocumentsCount")
    @ResponseBody
    public long getAllRejectUserDocumentsCount(@RequestParam(name = "userCode") String userCode) {
        return rejectedDocumentService.getRejectUserDocCount(userCode);
    }

}
