package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementStatus {
    private String status;
    private String companyName;
    private String annId;
    private LocalDateTime createdDt;
    private String announcementTitle;
}
