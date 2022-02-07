package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;


import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport.*;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdequacyFullReport {
    private List<ClarificationGeneralReport> clarificationReport;
    private List<WrongSubjectReport> wrongSubjects;
    private List<InternalReport> internalReport;
    private List<NonComplianceReport> nonComplianceReports;
    private List<SoftMailReport> softMailReports;
    private List<ExceptionalReport> exceptionalReports;
    private List<AnnouncementFullReport> announcementFullReports;
}
