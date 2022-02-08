package com.nse.listingcompliance.lcdebtannouncementservice.service;


import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.ocrkeyword.OcrKeywordResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Map;

public interface DebtOcrServiceInterface {
    ResponseEntity<ArrayList<OcrKeywordResponseBody>> getOcrKeywords(String announcementUuid);

    ResponseEntity<String> addKeywords(MultipartFile file,String announcementId, Map<String, String> header) throws CommonException;

}
