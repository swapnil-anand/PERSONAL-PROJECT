package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternalReport {
    private String applicationNo;
    private String companyName;
    private String categoryName;
    private String subjectName;
    private List<SubSubject> subSubjectType;
    private String announcementText;
    private String broadcastDate;
    private String broadcastTime;
    private String status;
    private String correctSubject;
    private List<InternalReportEmail> emailChain;
    private String announcementSummary;
}
