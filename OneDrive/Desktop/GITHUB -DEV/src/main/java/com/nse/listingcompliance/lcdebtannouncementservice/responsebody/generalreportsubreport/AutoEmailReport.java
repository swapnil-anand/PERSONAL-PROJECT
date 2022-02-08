package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AutoEmailReport {
    private String companyName;
    private String categoryName;
    private String subjectType;
    private List<SubSubject> subSubjectType;
    private Set<String> isinNo;
    private String dueDate;
    private Long noOfEmails;
    private LocalDateTime firstEmailSentOn;
    private String firstEmailSubject;
    private LocalDateTime secondEmailSentOn;
    private String secondEmailBy;
    private Set<String> emailSentTo;
}

