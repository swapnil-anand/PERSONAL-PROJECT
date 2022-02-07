package com.nse.listingcompliance.lcdebtannouncementservice.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nse.listingcompliance.lcdebtannouncementservice.config.ConfigErrorDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.constant.CommonMethods;
import com.nse.listingcompliance.lcdebtannouncementservice.constant.ErrorMessages;
import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;
import com.nse.listingcompliance.lcdebtannouncementservice.model.DocDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.model.dms.*;
import com.nse.listingcompliance.lcdebtannouncementservice.proxy.DMSService;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AnnouncementRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtFileServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

import static com.nse.listingcompliance.lcdebtannouncementservice.constant.CommonMethods.getObject;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class DebtFileService implements DebtFileServiceInterface {
    private DMSService dmsService;
    private AnnouncementRepository announcementRepo;

    @Autowired
    public DebtFileService(DMSService dmsService, AnnouncementRepository announcementRepo) {
        this.dmsService = dmsService;
        this.announcementRepo = announcementRepo;
    }

    @Override
    public Long createFolder(String announcementUuid) throws CommonException, JsonProcessingException {
        long lookInFolder = 0;
        String testFolder = "/Listing Compliance/Debt/DEBT/Testing/" + announcementUuid;
        CreateFolder folder= setFolderPayload(lookInFolder,testFolder, announcementUuid);
        return createDmsFolder(folder);
    }

    protected Object serviceNotAvailableException(ResponseEntity<?> response) throws CommonException {
        return getObject(response);
    }

    protected static CreateFolder setFolderPayload(long lookInFolder, String type, String announcementUuid) {
        return CreateFolder.builder()
                .lookInFolder(lookInFolder)
                .name(type)
                .option("DEBT/Testing/" + announcementUuid)
                .build();
    }

    protected Long createDmsFolder(CreateFolder createFolder) throws CommonException, JsonProcessingException {

        ResponseEntity<String> response = dmsService.createFolder(createFolder);
        String folderIndex = (String) serviceNotAvailableException(response);
        DmsIndex folder = new ObjectMapper().readValue(folderIndex, DmsIndex.class);
        return Long.valueOf(folder.getFolderIndex());

    }



    @Override
    public DocDetails uploadFile(Long folderIndex, String docType, MultipartFile file) throws CommonException {
        if(file==null){
            throw new CommonException(null, "File cannot be blank", INTERNAL_SERVER_ERROR);
        }
        return saveDocument( file, docType,folderIndex);//upload the document
    }

    private DocDetails saveDocument( MultipartFile document, String docType,long folderIndex) throws CommonException {

        NGOAddDocumentResponseBDO responseBody = uploadDocument(document, docType,folderIndex);
        NgogetDocListDocDataBDO doc = responseBody.getNgogetDocListDocDataBDO();
        String fileUid= CommonMethods.generateUUID();


        return DocDetails.builder()
                .id(fileUid)
                .docIndex(String.valueOf(doc.getDocumentIndex()))
                .build();
    }


    protected NGOAddDocumentResponseBDO uploadDocument(MultipartFile document, String docType, long folderIndex) throws CommonException {
        NgoAddDocumentBDO ngoAddDocumentBDO= NgoAddDocumentBDO.builder()
                .folderIndex(folderIndex)
                .documentName(document.getOriginalFilename())
                .comment("Document for " +docType)
                .createdByAppName(docType)
                .build();
        ResponseEntity<NGOAddDocumentResponseBDO> response= dmsService.upload(document, CommonMethods.convertObjectToString(ngoAddDocumentBDO));

        return (NGOAddDocumentResponseBDO) serviceNotAvailableException(response);
    }


    @Override
    public Document getDocument(String announcementUuid) throws CommonException {
        String folderIndex = this.announcementRepo.findForFolderIndex(announcementUuid);
        String docIndex = this.announcementRepo.findForDocIndex(announcementUuid);
        return this.downloadDocument(docIndex, folderIndex);
    }


    protected Document downloadDocument(String docIndex, String folderIndex) throws CommonException {
        ResponseEntity<DocumentResponse> response = dmsService.downloadDocument(DmsIndex.builder()
                .docIndex(docIndex)
                .folderIndex(folderIndex)
                .build());

        DocumentResponse responseBody = (DocumentResponse)serviceNotAvailableException(response);
        return Document.builder().fileData(Base64.getDecoder()
                .decode(responseBody.getDocContent()))
                .fileName(responseBody.getDocumentName()).build();
    }


}
