package com.nse.listingcompliance.lcdebtannouncementservice.model.dms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DeleteDocumentRespone implements Serializable {
    private String documentSize;
    private String error;
    private FailedDocuments failedDocuments;
    private String isindexes;
    private String loginUserIndex;
    private String option;
    private String status;
    private String userDBId;
}
