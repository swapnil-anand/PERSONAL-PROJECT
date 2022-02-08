package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class isin {
    private String isinNo;
    private String companyType;
}
