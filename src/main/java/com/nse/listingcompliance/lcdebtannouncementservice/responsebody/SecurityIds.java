package com.nse.listingcompliance.lcdebtannouncementservice.responsebody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecurityIds {
    private String secSymbol;
    private String secSeries;
    private String secStatus;
}
