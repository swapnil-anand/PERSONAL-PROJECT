package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.remarks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RemarkCheck {
    private Boolean proceedToVerification;
    private String nseOfficialId;
    private String remark;
}