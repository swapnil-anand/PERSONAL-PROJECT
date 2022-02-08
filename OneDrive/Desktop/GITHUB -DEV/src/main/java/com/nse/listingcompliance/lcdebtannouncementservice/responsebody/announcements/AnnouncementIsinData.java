package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementIsinData {
    private String isin;
    private LocalDateTime dueDate;
    private LocalDateTime paymentDate;
    private LocalDateTime dateOfBuyback;
    private String natureOfPayment;
    private String outstandingIssueSize;
    private String outstandingBonds;
    private LocalDateTime recordDate;
    private String noOfBonds;
}
