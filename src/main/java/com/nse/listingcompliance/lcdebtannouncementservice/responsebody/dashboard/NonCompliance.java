package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NonCompliance {
    private String nonComplianceType;
    private LocalDateTime nonComplianceDt;
    private List<String> nonComplianceTo;
    private String nonComplianceBody;
}
