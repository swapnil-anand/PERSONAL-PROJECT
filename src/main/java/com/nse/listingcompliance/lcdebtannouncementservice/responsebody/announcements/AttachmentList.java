package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachmentList {
    private Long fileId;
    private String fileName;
    private String fileType;
    private String fileStatus;
}
