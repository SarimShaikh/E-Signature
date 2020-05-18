package webApp.services;

import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.springframework.beans.factory.annotation.Value;
import webApp.utils.UtilsClass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

/**
 * Created by Sarim on 5/17/2020.
 */

@Service
@Log
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeneratePdfServiceImpl implements GeneratePdfService {

    static Logger logger = Logger.getLogger(GeneratePdfServiceImpl.class.getName());
    private final TemplateEngine templateEngine;
    @Value("${local.path}")
    private String filePath;

    @Override
    public InputStreamResource html2PdfGenerator(Map<String, Object> data, String docType) {
        Context context = new Context();
        context.setVariables(data);
        String html = "";
        String DEST = "";

        if (docType.equals("D")) {
            html = templateEngine.process("pdf-template/declarationPdf", context);
            logger.log(INFO, html);
            DEST = filePath + "DEC-" + UtilsClass.getLocalDate() + ".pdf";
        }
        else if (docType.equals("T")) {
            html = templateEngine.process("pdf-template/taxationPdf", context);
            logger.log(INFO, html);
            DEST = filePath + "TAX-" + UtilsClass.getLocalDate() + ".pdf";
        }

        try {
            HtmlConverter.convertToPdf(html, new FileOutputStream(DEST));
            return new InputStreamResource(new FileInputStream(DEST));

        } catch (IOException e) {
            logger.log(SEVERE, e.getMessage(), e);
            return null;
        }
    }
}
