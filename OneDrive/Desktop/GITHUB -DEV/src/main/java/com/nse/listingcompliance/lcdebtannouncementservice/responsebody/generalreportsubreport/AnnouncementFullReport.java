package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;

import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.DocUploadDetails;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementFullReport {
    private String applicationNo;
    private String companyName;
    private String categoryName;
    private String subjectName;
    private List<SubSubject> subSubjectType;
    private String announcementText;
    private String announcementSummary;
    private String pdfText;
    private String status;
    private String announcementCreatedBy;
    private String adequacyType;
    private LocalDateTime adequacyDateTime;
    private String handledBy;
    private LocalDateTime verifiedDateTime;
    private String broadcastDate;
    private String broadcastTime;
    private String receivedDate;
    private String receivedTime;
    private String exchangeRemark;
    private String companyRemark;
    private String pdfMachineReadable;
    private String tillDisseminatedTime;
    private List<String> genericKeywords;
    private List<String> subjectWiseKeyword;
    private DocUploadDetails attachedFile;
}
