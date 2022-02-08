package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adequacy {
    private String applicationNumber;
    private Long orgId;
    private String companyName;
    private String category;
    private String subjectType;
    private String subSubjectType;
    private String announcementText;
    private String announcementSummary;
    private String remarksForExchange;
    private String critical;
    private String receivedDate;
    private String receivedTime;
    private String anDate;
    private String anTime;
    private String timeTaken;
    private String announcementCreatedBy;
    private String status;
    private String adequacyCreationDate;
    private String adequacyCreationTime;

    private String annAttachment;
    private String annAttachmentMachine;

    private String lastUpdateDate;
    private List<String> genericKeywords;
    private List<String> subjectWiseKeywords;

    private String adequacyType;
    private String lockedBy;
    private String handledBy;
    private String internalRemarks;

    private String communicationType;
    private List<String> parameters;
    private List<String> subParameters;

    private String nameOfVerifier;
    private LocalDateTime verifiedDatetime;
    private LocalDateTime dateOfClarification;
    private String exceptionReporting;
    private String exceptionReportingRemarks;
    private String exceptionReportingAttachment;
    private LocalDateTime dateOfLatestResponse;
    private LocalDateTime dateOfResponseReceived;
    private LocalDateTime dateOfResponseDisseminated;
    private String listedCompanyResponse;
    private String listedCompanyResponseAttachment;
    private String completedBy;
    private String auditTrail;
    private String pdfText;
    private String pdfMachineRead;
}
