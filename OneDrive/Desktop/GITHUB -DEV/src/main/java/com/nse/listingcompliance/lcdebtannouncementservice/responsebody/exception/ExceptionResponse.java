package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.exception;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {
    private Long exceptionId;
    private String remarks;
    private List<Attachment> attachment;
}