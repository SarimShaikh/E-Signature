package webApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webApp.entities.TaxDocument;
import webApp.entities.dto.DocumentDto;
import webApp.repositories.TaxDocumentRepository;
import webBase.service.ServiceBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Syed Asher Ahmed on 5/10/2020.
 */

@Service
public class TaxDocumentService extends ServiceBase<TaxDocument, Long> {
    private TaxDocumentRepository taxDocumentRepository;

    @Autowired
    public TaxDocumentService(TaxDocumentRepository repository) {
        super(repository);
        taxDocumentRepository = repository;
    }

    public List<DocumentDto> getAllPendingTaxation() {
        List<Map<String, Object>> list = taxDocumentRepository.getAllPendingTaxDocuments();
        List<DocumentDto> beanList = new ArrayList<>();

        for (Map<String, Object> item : list) {
            DocumentDto documentDto = new DocumentDto();
            TaxDocument taxDocument = new TaxDocument();
            taxDocument.setTaxDocumentCode(((Integer) item.get("taxDocumentCode")).longValue());
            taxDocument.setUserCode(((Integer) item.get("userCode")).longValue());
            taxDocument.setDocumentName((String)item.get("documentName"));
            taxDocument.setAbnNumber((String)item.get("abnNumber"));
            taxDocument.setCompanyName((String)item.get("companyName"));
            taxDocument.setBussinessClient((String)item.get("bussinessClient"));
            taxDocument.setContactNo((String)item.get("contactNo"));
            taxDocument.setPostCode((String)item.get("postCode"));
            taxDocument.setBankName((String)item.get("bankName"));
            taxDocument.setBsbNumber((String)item.get("bsbNumber"));
            taxDocument.setAccountNo((String)item.get("accountNo"));
            taxDocument.setSignatureCode((String)item.get("signatureCode"));
            taxDocument.setSignature((String)item.get("signature"));
            taxDocument.setSignatureFonts((String)item.get("signatureFonts"));
            taxDocument.setBussinessAddress((String)item.get("bussinessAddress"));
            taxDocument.setHomeAddress((String)item.get("homeAddress"));
            taxDocument.setDocumentStatus((String)item.get("documentStatus"));
            taxDocument.setApprovedBy((String)item.get("approvedBy"));
            taxDocument.setIsActive((Byte)item.get("isActive"));
            taxDocument.setCreatedBy((String)item.get("createdBy"));
            taxDocument.setCreatedDate((Date)item.get("Date"));
            taxDocument.setModifiedBy((String)item.get("modifiedBy"));
            taxDocument.setModifiedDate((Date)item.get("modifiedDate"));

            documentDto.setUserName((String)item.get("userName"));
            documentDto.setUserEmail((String)item.get("userEmail"));
            documentDto.setEntity(taxDocument);
            beanList.add(documentDto);
        }
        return beanList;
    }

    public List<DocumentDto> getAllApprovedTaxation() {
        List<Map<String, Object>> list = taxDocumentRepository.getAllApprovedTaxDocuments();
        List<DocumentDto> beanList = new ArrayList<>();

        for (Map<String, Object> item : list) {
            DocumentDto documentDto = new DocumentDto();
            TaxDocument taxDocument = new TaxDocument();
            taxDocument.setTaxDocumentCode(((Integer) item.get("taxDocumentCode")).longValue());
            taxDocument.setUserCode(((Integer) item.get("userCode")).longValue());
            taxDocument.setDocumentName((String)item.get("documentName"));
            taxDocument.setAbnNumber((String)item.get("abnNumber"));
            taxDocument.setCompanyName((String)item.get("companyName"));
            taxDocument.setBussinessClient((String)item.get("bussinessClient"));
            taxDocument.setContactNo((String)item.get("contactNo"));
            taxDocument.setPostCode((String)item.get("postCode"));
            taxDocument.setBankName((String)item.get("bankName"));
            taxDocument.setBsbNumber((String)item.get("bsbNumber"));
            taxDocument.setAccountNo((String)item.get("accountNo"));
            taxDocument.setSignatureCode((String)item.get("signatureCode"));
            taxDocument.setSignature((String)item.get("signature"));
            taxDocument.setSignatureFonts((String)item.get("signatureFonts"));
            taxDocument.setBussinessAddress((String)item.get("bussinessAddress"));
            taxDocument.setHomeAddress((String)item.get("homeAddress"));
            taxDocument.setDocumentStatus((String)item.get("documentStatus"));
            taxDocument.setApprovedBy((String)item.get("approvedBy"));
            taxDocument.setIsActive((Byte)item.get("isActive"));
            taxDocument.setCreatedBy((String)item.get("createdBy"));
            taxDocument.setCreatedDate((Date)item.get("Date"));
            taxDocument.setModifiedBy((String)item.get("modifiedBy"));
            taxDocument.setModifiedDate((Date)item.get("modifiedDate"));

            documentDto.setUserName((String)item.get("userName"));
            documentDto.setUserEmail((String)item.get("userEmail"));
            documentDto.setEntity(taxDocument);
            beanList.add(documentDto);
        }
        return beanList;
    }

    public long getApprovedTaxDocCount(){
        long approvedDocsCount = taxDocumentRepository.getAllApprovedTaxDocumentsCount();
        return approvedDocsCount;
    }

    public long getPendingTaxDocCount(){
        long pendingDocsCount = taxDocumentRepository.getAllPendingTaxDocumentsCount();
        return pendingDocsCount;
    }
}
