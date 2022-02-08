package com.nse.listingcompliance.lcdebtannouncementservice.service;

import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.exception.ExceptionRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.parameter.ParameterRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.exception.ExceptionResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster.ParameterResponseBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;

import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface DebtReviewServiceInterface {

    ResponseEntity<Status> saveException(String announcementUuid, ExceptionRequest exception, Map<String, String> header);

    ResponseEntity<ParameterResponseBody> getParameterList(Long subjectId, Map<String, String> header);

    ResponseEntity<Status> saveParameters(ParameterRequest params, Map<String, String> header);

    ResponseEntity<ExceptionResponse> getExceptionReporting(String announcementUuid, Map<String, String> header);


}
