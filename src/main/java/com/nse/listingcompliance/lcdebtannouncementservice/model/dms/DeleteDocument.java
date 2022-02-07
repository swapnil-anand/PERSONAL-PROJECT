package com.nse.listingcompliance.lcdebtannouncementservice.model.dms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DeleteDocument implements Serializable {
    private String documentIndex;
    private Long parentFolderIndex;
    private String referenceFlag;
}
