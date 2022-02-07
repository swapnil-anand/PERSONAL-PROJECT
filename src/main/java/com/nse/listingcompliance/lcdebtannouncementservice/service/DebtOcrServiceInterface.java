package com.nse.listingcompliance.lcdebtannouncementservice.service;


import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.ocrkeyword.OcrKeywordResponseBody;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface DebtOcrServiceInterface {
    ResponseEntity<ArrayList<OcrKeywordResponseBody>> getOcrKeywords(String announcementUuid);

    void addOcr(String announcement);
}
