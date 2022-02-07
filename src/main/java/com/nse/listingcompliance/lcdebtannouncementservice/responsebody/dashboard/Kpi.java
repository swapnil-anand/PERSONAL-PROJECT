package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Kpi {
    private Long drafted;
    private Long markedVerification;
    private Long verified;
    private Long changeSuggested;
    private Long critical;
    private Long responseReceived;
    private Long disseminated;
}