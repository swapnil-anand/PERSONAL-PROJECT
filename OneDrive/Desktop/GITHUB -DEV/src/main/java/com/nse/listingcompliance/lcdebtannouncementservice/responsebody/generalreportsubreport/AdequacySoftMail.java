package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdequacySoftMail {
    private Boolean softMail;
    private LocalDateTime softMailDt;
    private List<String> softMailTo;
    private String softMailBody;
}