package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternalReportEmail {
    private LocalDateTime emailSentOn;
    private String emailSentBy;
    private String emailSentTo;
}
