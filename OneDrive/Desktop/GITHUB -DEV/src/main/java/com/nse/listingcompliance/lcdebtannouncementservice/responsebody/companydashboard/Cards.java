package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cards {
    private Long disseminated;
    private Long clarificationRequested;
    private Long responsePending;
    private Long drafted;
}
