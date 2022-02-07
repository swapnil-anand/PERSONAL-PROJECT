package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasicFields {
    private String applicationNo;
    private String companyName;
    private String categoryName;
    private String subjectName;
    private LocalDateTime broadcastDt;
    private LocalDateTime adequacyCreateDt;
    private String status;
    private String adequacyType;
    private String handledBy;
    private String verifiedBy;
    private LocalDateTime verifiedTime;
    private String exchangeRemarks;
    private String remarksForExchange;
}
