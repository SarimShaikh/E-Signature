package webApp.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webApp.services.GeneratePdfServiceImpl;

import java.util.Map;

/**
 * Created by Sarim on 5/17/2020.
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log()
public class GeneratePdfController {

    private final GeneratePdfServiceImpl documentGeneratorService;

    @RequestMapping(value = "/generate-pdf", method = RequestMethod.POST, produces = "application/pdf")
    public ResponseEntity html2pdf(@RequestBody Map<String, Object> data, @RequestParam(name = "docType") String docType) {
        InputStreamResource resource = documentGeneratorService.html2PdfGenerator(data, docType);
        if (resource != null) {
            return ResponseEntity
                    .ok()
                    .body(resource);
        } else {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
