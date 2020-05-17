package webApp.services;

import org.springframework.core.io.InputStreamResource;
import java.util.Map;

/**
 * Created by Sarim on 5/17/2020.
 */
public interface GeneratePdfService {

    InputStreamResource html2PdfGenerator(Map<String, Object> data , String docType);
}
