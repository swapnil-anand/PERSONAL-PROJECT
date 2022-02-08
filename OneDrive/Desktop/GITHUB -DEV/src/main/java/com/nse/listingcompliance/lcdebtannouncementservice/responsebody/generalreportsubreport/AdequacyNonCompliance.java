package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdequacyNonCompliance {
    private Boolean nonCompliance;
    private LocalDateTime nonComplianceDt;
    private List<String> nonComplianceTo;
    private String nonComplianceBody;
}
