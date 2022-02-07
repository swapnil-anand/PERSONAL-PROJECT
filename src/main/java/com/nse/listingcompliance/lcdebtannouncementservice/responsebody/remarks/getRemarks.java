package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class getRemarks {
    private String nseOfficialId;
    private String comment;
}
