package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IsinResponseBody {
    private String isin;
    private LocalDateTime dateOfBuyback;
    private LocalDateTime dueDate;
    private LocalDateTime paymentDate;
    private String natureOfPayment;
    private String outstandingIssueSize;
    private String outstandingBonds;
    private LocalDateTime recordDate;
    private String noOfBonds;
}
