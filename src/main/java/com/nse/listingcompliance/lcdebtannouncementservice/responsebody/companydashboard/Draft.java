package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Draft {
    private String applicationNumber;
    private String category;
    private String subjectType;
    private String announcementText;
    private String createdBy;
    private String lastUpdatedBy;
    private LocalDateTime updatedDate;
    private String annStatus;
}
