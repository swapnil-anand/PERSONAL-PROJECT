package com.nse.listingcompliance.lcdebtannouncementservice.model.dms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class NGOAddDocumentResponseBDO {
    private String message;
    private NgogetDocListDocDataBDO ngogetDocListDocDataBDO;
    private String statusCode;
}
