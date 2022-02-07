package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;

import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.DocUploadDetails;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionalReport {
    private String applicationNo;
    private String companyName;
    private String categoryName;
    private String subjectName;
    private String subSubjectType;
    private String announcementText;
    private String receivedDate;
    private String receivedTime;
    private String broadcastDate;
    private String broadcastTime;
    private String status;
    private String exchangeRemark;
    private String exceptionalRemark;
    private List<DocUploadDetails> exceptionAttachments;
    private String announcementSummary;
}