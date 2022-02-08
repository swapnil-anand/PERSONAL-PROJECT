package com.nse.listingcompliance.lcdebtannouncementservice.model.dms;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


/**
 *
 */
@Data
@Getter
@Setter
@NoArgsConstructor
public class DocumentResponse implements Serializable {
    private String docContent;
    private String documentType;
    private String documentName;
    private String documentSize;
    private String message;
    private String createdByAppName;
    private String statusCode;
}
