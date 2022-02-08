package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachmentStatus {
    private String attachmentType;
    private String announcementId;
    private Long id;
}
