package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdequacyFullReport {
    private String applicationNo;
    private String companyName;
    private String categoryName;
    private String subjectName;
    private List<SubSubject> subSubjectList;
    private LocalDateTime broadcastDt;
    private LocalDateTime adequacyDt;
    private String applicationStatus;
    private String adequacyType;
    private String handledBy;
    private String verifiedBy;
    private LocalDateTime verifiedDt;
    private String exchangeRemarks;
    private String announcementRemark;
    private List<AdequacySubParameter> parameters;
    private List<AdequacyClarification> clarifications;
    private List<AdequacySoftMail> softMails;
    private List<AdequacyNonCompliance> nonCompliance;
    private List<AdequacyInternal> internals;
    private List<AdequacyWrongSubject> wrongSubjects;
    private AdequacyMisc miscs;
}
