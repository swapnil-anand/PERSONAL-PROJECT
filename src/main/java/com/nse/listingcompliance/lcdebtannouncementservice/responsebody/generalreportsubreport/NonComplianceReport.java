package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NonComplianceReport {
    private String applicationNo;
    private String companyName;
    private String categoryName;
    private String subjectName;
    private String subSubjectType;
    private String announcementText;
    private String broadcastDate;
    private String broadcastTime;
    private String status;
    private String nonCompliance;
    private List<InternalReportEmail> emailChain;
    private String announcementSummary;
}

