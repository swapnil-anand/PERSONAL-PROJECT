package com.nse.listingcompliance.lcdebtannouncementservice.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
