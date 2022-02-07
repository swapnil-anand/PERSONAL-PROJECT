package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialReport {
    private String companyName;
    private LocalDateTime periodEnded;
    private String orgId;
    private String companyStatus;
    private LocalDateTime financialYearFrom;
    private LocalDateTime financialYearTo;
    private String periodType;
    private LocalDateTime frDateOfCompliance;
    private LocalDateTime frLastDateOfSubmission;
    private String frAdequacyCheck;
    private LocalDateTime frDateOfResubmission;
    private String frComplianceStatus;
    private LocalDateTime dateOfCompliance52_5;
    private LocalDateTime lastDateOfSubmission52_5;
    private String adequacyCheck52_5;
    private LocalDateTime dateOfResubmission52_5;
    private String complianceStatus52_5;
    private LocalDateTime dateOfCompliance52_6;
    private LocalDateTime lastDateOfSubmission52_6;
    private String adequacyCheck52_6;
    private LocalDateTime dateOfResubmission52_6;
    private String complianceStatus52_6;
    private LocalDateTime dateOfCompliance52_7;
    private LocalDateTime lastDateOfSubmission52_7;
    private String adequacyCheck52_7;
    private LocalDateTime dateOfResubmission52_7;
    private String complianceStatus52_7;
    private String confirmationStatus;
    private String remarks;
}
