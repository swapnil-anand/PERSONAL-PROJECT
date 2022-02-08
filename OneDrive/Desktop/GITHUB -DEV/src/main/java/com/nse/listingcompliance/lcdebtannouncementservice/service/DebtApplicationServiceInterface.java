package com.nse.listingcompliance.lcdebtannouncementservice.service;

import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement.IsinRequestBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.IsinResponseBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.IsinNoDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata.MasterData;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.security.SymbolResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DebtApplicationServiceInterface {
    ResponseEntity<MasterData> getSubjectsAndCategory(String companyType, Map<String,String> header);
    ResponseEntity<List<IsinResponseBody>> getIsinByAnnouncementId(String announcementUuid, Map<String,String> header);
    ResponseEntity<Status> saveIsin(String announcementUuid, List<IsinRequestBody> isinRequest, Map<String,String> header);
    ResponseEntity<IsinNoDetails> getNeapsIsin(Map<String,String> header);
    ResponseEntity<List<SymbolResponse>> getAllCompany(Long catId, Map<String,String> header);
}
