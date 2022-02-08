package com.nse.listingcompliance.lcdebtannouncementservice.controller;


import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.exception.ExceptionRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.parameter.ParameterRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.exception.ExceptionResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster.ParameterResponseBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtReviewServiceInterface;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin
@Api(value = "LC Debt Service", tags = "Review")
@Slf4j
public class DebtReviewController {

    private DebtReviewServiceInterface debtReviewService;

    @Autowired
    public DebtReviewController(DebtReviewServiceInterface debtReviewService) {
        this.debtReviewService = debtReviewService;
    }

    @PostMapping("/review/announcements/{announcementUuid}/exceptionReporting")
    public ResponseEntity<Status> saveException(@PathVariable("announcementUuid") String announcementUuid,
                                                @RequestBody ExceptionRequest exception,
                                                @RequestHeader Map<String,String> header){
        return this.debtReviewService.saveException(announcementUuid, exception, header);
    }

    @GetMapping("/review/announcements/{announcementUuid}/exceptionReporting/get")
    public ResponseEntity<ExceptionResponse> getException(@PathVariable("announcementUuid") String announcementUuid,
                                                          @RequestHeader Map<String,String> header){
        return this.debtReviewService.getExceptionReporting(announcementUuid, header);
    }

    @GetMapping("/review/announcements/{subjectId}/parameters")
    public ResponseEntity<ParameterResponseBody> getParameters(@PathVariable("subjectId") Long subjectId,
                                                               @RequestHeader Map<String,String> header) {
        return this.debtReviewService.getParameterList(subjectId, header);
    }

    @PostMapping("/review/announcements/parameter/save")
    public ResponseEntity<Status> saveParameters(@RequestBody ParameterRequest params,
                                                 @RequestHeader Map<String,String> header) {
        return this.debtReviewService.saveParameters(params, header);
    }

}
