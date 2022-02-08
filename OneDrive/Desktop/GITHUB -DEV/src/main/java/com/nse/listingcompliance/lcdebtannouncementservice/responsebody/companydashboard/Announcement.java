package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    private String announcementId;
    private String categoryName;
    private String subjectName;
    private String announcementText;
    private String announcementStatus;
    private LocalDateTime submissionDt;
    private LocalDateTime broadcastDt;
    private String attachmentName;
}
