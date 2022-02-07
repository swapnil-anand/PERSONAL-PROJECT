package com.nse.listingcompliance.lcdebtannouncementservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.email.Email;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.email.EmailResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.communication.InitializeCommRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.communication.Communication;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.initializes.CommonInitialize;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtCommunicationServiceInterface;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Api(value = "LC Debt Service", tags = "Communication")
@Slf4j
public class DebtCommunicationController {
    private DebtCommunicationServiceInterface debtComm;

    @Autowired
    public DebtCommunicationController(DebtCommunicationServiceInterface debtComm) {this.debtComm = debtComm;}

    @GetMapping("/communication/{announcementUuid}")
    public ResponseEntity<List<Communication>> getCommunicationList(@PathVariable("announcementUuid") String announcementUuid, @RequestHeader Map<String,String> header) {
        return this.debtComm.getCommunicationHistory(announcementUuid, header);
    }

    @PostMapping("/communication/response/")
    public ResponseEntity<Status> saveCommunicationResponse(@RequestBody EmailResponse response, @RequestHeader Map<String, String> header){
        return this.debtComm.saveCommunicationResponse(response, header);
    }

    @PostMapping("/communication/{announcementUuid}")
    public ResponseEntity<Status> saveCommunication(@PathVariable("announcementUuid") String announcementUuid,
                                           @RequestBody Email email, @RequestHeader Map<String, String> header ) throws CommonException, JsonProcessingException {
        return this.debtComm.saveEmail(announcementUuid, email, header);
    }

    @PostMapping("/communication/initialize")
    public ResponseEntity<CommonInitialize> initializeCommunication(@RequestBody InitializeCommRequest communication, @RequestHeader Map<String, String> header){
        return this.debtComm.initializeCommunication(communication, header);
    }

}
