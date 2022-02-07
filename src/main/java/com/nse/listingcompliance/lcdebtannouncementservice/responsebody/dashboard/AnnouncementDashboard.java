package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDashboard {
    private String applicationNumber;
    private String companyName;
    private String companyType;
    private String category;
    private String subjectType;
    private String subSubjectType;
    private String announcementText;
    private String annCreatedBy;
    private String annStatus;
    private String adequacyStatus;
    private LocalDateTime submissionDate;
    private LocalDateTime broadcastDate;
    private String attachmentPdf;
    private String acknowledgementPdf;
}
