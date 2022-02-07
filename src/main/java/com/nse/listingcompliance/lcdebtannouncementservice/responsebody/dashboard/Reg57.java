package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reg57 {
    private String isin;
    private String companyName;
    private String securityType;
    private String publicOrPrivate;
    private String tradeStatus;
    private String listingStatus;
    private LocalDateTime dateOfSuspension;
    private LocalDateTime maturityDate;
    private LocalDateTime offerDocumentDate;
    private Long issueSize;
    private Long numberOfBonds;
    private LocalDateTime systemDueDate;
    private String natureOfPayment;
    private LocalDateTime lastDateOfSubmission;
    private LocalDateTime userDueDate;
    private LocalDateTime paymentDate;
    private LocalDateTime defaultDate;

    private String userNatureOfPayment;
    private String buybackType;
    private LocalDateTime intimationDate;
    private String applicationNumber;
    private String complianceStatus;
    private String confirmationStatus;
    private String remarks;
}
