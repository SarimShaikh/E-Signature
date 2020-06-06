package webApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webApp.entities.DeclarationDocument;
import webApp.entities.dto.DocumentDto;
import webApp.repositories.DeclarationDocumentRepository;
import webBase.service.ServiceBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Sarim on 5/7/2020.
 */
@Service
public class DeclarationDocumentService extends ServiceBase<DeclarationDocument, Long> {

    private DeclarationDocumentRepository declarationDocumentRepository;

    @Autowired
    public DeclarationDocumentService(DeclarationDocumentRepository repository) {
        super(repository);
        declarationDocumentRepository = repository;
    }

    public List<DocumentDto> getAllpendingDeclaration() {
        List<Map<String, Object>> list = declarationDocumentRepository.getAllPendingDeclarationDocuments();
        List<DocumentDto> beanList = new ArrayList<>();

        for (Map<String, Object> item : list) {
             DocumentDto documentDto = new DocumentDto();
             DeclarationDocument declarationDocument = new DeclarationDocument();
             declarationDocument.setDocumentCode(((Integer) item.get("documentCode")).longValue());
             declarationDocument.setUserCode(((Integer) item.get("userCode")).longValue());
             declarationDocument.setDocumentName((String)item.get("documentName"));
             declarationDocument.setAbnNumber((String)item.get("abnNumber"));
             declarationDocument.setBussinessClient((String)item.get("bussinessClient"));
             declarationDocument.setBsbNumber((String)item.get("bsbNumber"));
             declarationDocument.setAgentName((String)item.get("agentName"));
             declarationDocument.setDocumentPeriod((String)item.get("documentPeriod"));
             declarationDocument.setPaperDeclareDate((String)item.get("paperDeclareDate"));
             declarationDocument.setBussinessAddress((String)item.get("bussinessAddress"));
             declarationDocument.setDocumentStatus((String)item.get("documentStatus"));
             declarationDocument.setApprovedBy((String)item.get("approvedBy"));
             declarationDocument.setSignatureCode((String)item.get("signatureCode"));
             declarationDocument.setSignature((String)item.get("signature"));
             declarationDocument.setSignatureFonts((String)item.get("signatureFonts"));
             declarationDocument.setIsActive((Byte)item.get("isActive"));
             declarationDocument.setCreatedBy((String)item.get("createdBy"));
             declarationDocument.setCreatedDate((Date)item.get("Date"));
             declarationDocument.setModifiedBy((String)item.get("modifiedBy"));
             declarationDocument.setModifiedDate((Date)item.get("modifiedDate"));

             documentDto.setUserName((String)item.get("userName"));
             documentDto.setUserEmail((String)item.get("userEmail"));
             documentDto.setEntity(declarationDocument);
             beanList.add(documentDto);
        }
        return beanList;
    }

    public List<DocumentDto> getAllApprovedDeclaration() {
        List<Map<String, Object>> list = declarationDocumentRepository.getAllApprovedDeclarationDocuments();
        List<DocumentDto> beanList = new ArrayList<>();

        for (Map<String, Object> item : list) {
            DocumentDto documentDto = new DocumentDto();
            DeclarationDocument declarationDocument = new DeclarationDocument();
            declarationDocument.setDocumentCode(((Integer) item.get("documentCode")).longValue());
            declarationDocument.setUserCode(((Integer) item.get("userCode")).longValue());
            declarationDocument.setDocumentName((String)item.get("documentName"));
            declarationDocument.setAbnNumber((String)item.get("abnNumber"));
            declarationDocument.setBussinessClient((String)item.get("bussinessClient"));
            declarationDocument.setBsbNumber((String)item.get("bsbNumber"));
            declarationDocument.setAgentName((String)item.get("agentName"));
            declarationDocument.setDocumentPeriod((String)item.get("documentPeriod"));
            declarationDocument.setPaperDeclareDate((String)item.get("paperDeclareDate"));
            declarationDocument.setBussinessAddress((String)item.get("bussinessAddress"));
            declarationDocument.setDocumentStatus((String)item.get("documentStatus"));
            declarationDocument.setApprovedBy((String)item.get("approvedBy"));
            declarationDocument.setSignatureCode((String)item.get("signatureCode"));
            declarationDocument.setSignature((String)item.get("signature"));
            declarationDocument.setSignatureFonts((String)item.get("signatureFonts"));
            declarationDocument.setIsActive((Byte)item.get("isActive"));
            declarationDocument.setCreatedBy((String)item.get("createdBy"));
            declarationDocument.setCreatedDate((Date)item.get("Date"));
            declarationDocument.setModifiedBy((String)item.get("modifiedBy"));
            declarationDocument.setModifiedDate((Date)item.get("modifiedDate"));

            documentDto.setUserName((String)item.get("userName"));
            documentDto.setUserEmail((String)item.get("userEmail"));
            documentDto.setEntity(declarationDocument);
            beanList.add(documentDto);
        }
        return beanList;
    }

    public long getApprovedDecDocCount(){
        long approvedDocsCount = declarationDocumentRepository.getAllApprovedDecDocumentsCount();
        return approvedDocsCount;
    }

    public long getPendingDecDocCount(){
        long pendingDocsCount = declarationDocumentRepository.getAllPendingDecDocumentsCount();
        return pendingDocsCount;
    }
}
