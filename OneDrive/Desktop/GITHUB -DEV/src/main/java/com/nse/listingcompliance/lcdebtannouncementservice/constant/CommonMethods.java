package com.nse.listingcompliance.lcdebtannouncementservice.constant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nse.listingcompliance.lcdebtannouncementservice.config.ConfigErrorDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class CommonMethods {
    private static final Logger log = LoggerFactory.getLogger(CommonMethods.class);
    private CommonMethods(){}
    public static CommonException throwError(ConfigErrorDetails.ErrorDetails errorMapping) {
        return new CommonException(errorMapping.getErrorCode(), errorMapping.getMessage(),
                HttpStatus.resolve(errorMapping.getHttpStatusCode()));
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String convertObjectToString(Object request) {
        String asString = null;
        try {
            asString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request);
        } catch (JsonProcessingException ignored) {
            log.error("ERROR WHILE CONVERTING TO STRING");
        }
        return asString;
    }

    public static Object serviceNotAvailableException(ResponseEntity<?> response) throws CommonException {
        return getObject(response);
    }

    public static Object getObject(ResponseEntity<?> response) throws CommonException {
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            ConfigErrorDetails.ErrorDetails errorDetails = new ConfigErrorDetails.ErrorDetails();
            errorDetails.setErrorCode("");
            errorDetails.setHttpStatusCode(424);
            errorDetails.setMessage(ErrorMessages.BATCH_JOB_ERROR);
            throw CommonMethods.throwError(errorDetails);
        }
    }

}
