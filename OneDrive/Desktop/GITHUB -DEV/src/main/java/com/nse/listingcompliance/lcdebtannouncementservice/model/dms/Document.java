package com.nse.listingcompliance.lcdebtannouncementservice.model.dms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Request type sample document.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Document {
    private Long docIndex;
    private String fileName;
    private String createdByAppName;
    private byte[] fileData;
}
