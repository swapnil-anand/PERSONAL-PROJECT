package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionRequest {
    private Long attachmentId;
    private String nseOfficialId;
    private String comment;
    private Boolean inputStatus;
}
