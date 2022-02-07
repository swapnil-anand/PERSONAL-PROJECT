package com.nse.listingcompliance.lcdebtannouncementservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.email.Email;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.email.EmailResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.communication.InitializeCommRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.communication.Communication;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.initializes.CommonInitialize;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DebtCommunicationServiceInterface {
    ResponseEntity<List<Communication>> getCommunicationHistory(String announcementUuid, Map<String, String> header);
    ResponseEntity<Status> saveCommunicationResponse(EmailResponse response, Map<String, String> header);
    ResponseEntity<Status> saveEmail(String announcementUuid, Email email, Map<String, String> header) throws CommonException, JsonProcessingException;
    ResponseEntity<CommonInitialize> initializeCommunication(InitializeCommRequest communication, Map<String,String> header);
}
