package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IsinRequestBody {
    private String isin;
    private String dateOfBuyback;
    private String dueDate;
    private String paymentDate;
    private String natureOfPayment;
    private String outstandingIssueSize;
    private String outstandingBonds;
    private String recordDate;
    private String noOfBonds;
}
