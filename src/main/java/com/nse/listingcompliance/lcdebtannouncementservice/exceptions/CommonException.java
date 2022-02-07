package com.nse.listingcompliance.lcdebtannouncementservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CommonException extends Exception {
    private String errorCode;
    private String message;
    private HttpStatus status;

    public CommonException() { }

    public CommonException(String errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }
}
