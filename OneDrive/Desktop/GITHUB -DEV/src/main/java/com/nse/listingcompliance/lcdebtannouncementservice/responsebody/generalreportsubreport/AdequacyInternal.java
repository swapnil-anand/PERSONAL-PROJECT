package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;


import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdequacyInternal {
    private Boolean internal;
    private LocalDateTime internalDt;
    private Set<String> internalTo;
    private String internalBody;
}