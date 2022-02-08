package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reg50 {
    private String isin;
    private String companyName;
    private String companyStatus;
    private String securityType;
    private String publicOrPrivate;
    private String tradeStatus;
    private String listingStatus;
    private LocalDateTime dateOfSuspension;
    private LocalDateTime maturityDate;
    private LocalDateTime systemDueDate;
    private String natureOfPayment;
    private LocalDateTime lastDateOfSubmission;
    private LocalDateTime userDueDate;
    private String userNatureOfPayment;
    private LocalDateTime intimationDate50;
    private String applicationNumber;
    private String complianceStatus;
    private String confirmationStatus;
}
