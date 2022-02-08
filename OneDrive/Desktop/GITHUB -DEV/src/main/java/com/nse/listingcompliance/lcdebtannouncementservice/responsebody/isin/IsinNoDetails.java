package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IsinNoDetails {
    private List<isin> publicIsin;
    private List<isin> privateIsin;
}
