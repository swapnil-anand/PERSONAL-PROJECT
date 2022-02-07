package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reg60 {
    private String isin;
    private String companyName;
    private String securityType;
    private String publicOrPrivate;
    private String tradeStatus;
    private String listingStatus;
    private LocalDateTime suspensionDate;
    private LocalDateTime maturityDate;
    private String rdBcInterestPaymentRedemption;
    private LocalDateTime systemDueDate;
    private String natureOfPayment;
    private LocalDateTime submissionLastDate;
    private LocalDateTime userDueDate;
    private LocalDateTime userRecordDate;
    private String userNatureOfPayment;
    private LocalDateTime intimationDate60;
    private String applicationNumber;
    private String complianceStatus;

}
