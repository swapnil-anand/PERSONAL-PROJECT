package com.nse.listingcompliance.lcdebtannouncementservice.model.dms;

import lombok.*;


@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Setter
public class NgoAddDocumentBDO {
    private long folderIndex;
    private String documentName;
    private String createdByAppName;
    private String comment;
}