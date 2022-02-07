package com.nse.listingcompliance.lcdebtannouncementservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;
import com.nse.listingcompliance.lcdebtannouncementservice.model.DocDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.model.dms.Document;
import org.springframework.web.multipart.MultipartFile;

public interface DebtFileServiceInterface {

    Long createFolder(String announcementUuid) throws CommonException, JsonProcessingException;

    DocDetails uploadFile(Long folderIndex, String docType, MultipartFile file)
            throws CommonException, JsonProcessingException;

    Document getDocument(String announcementUuid) throws CommonException;
}
