package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementResponseBody {
    private Long categoryId;
    private Long subjectTypeId;
    private List<AnnouncementDataFields> dataFields;
    private List<AnnouncementIsinData> isinData;
    private String announcementText;
    private String attachmentId;
    private String remarks;
    private String announcementStatus;
    private String createdBy;
    private LocalDateTime createdDt;
    private String updatedBy;
    private LocalDateTime updatedDt;

}