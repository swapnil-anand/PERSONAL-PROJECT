package com.nse.listingcompliance.lcdebtannouncementservice.model.dms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DeleteDocumentRequest implements Serializable {
    private String cabinetName;
    private Documents documents;
    private String option;
    private String userDBId;
}
