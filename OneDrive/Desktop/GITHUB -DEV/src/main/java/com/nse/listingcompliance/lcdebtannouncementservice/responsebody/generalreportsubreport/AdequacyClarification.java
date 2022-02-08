package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdequacyClarification {
    private Boolean clarification;
    private LocalDateTime clarificationDt;
    private Set<String> clarificationTo;
    private String clarificationBody;
    private LocalDateTime clarificationResponseDt;
    private Long noOfResponse;
}
