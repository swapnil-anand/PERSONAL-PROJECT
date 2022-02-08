package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pending {
    private String applicationNumber;
    private String subjectType;
    private LocalDateTime submissionDate;
    private LocalDateTime clarificationDate;
    private String announcementText;
    private String communicationType;
}
