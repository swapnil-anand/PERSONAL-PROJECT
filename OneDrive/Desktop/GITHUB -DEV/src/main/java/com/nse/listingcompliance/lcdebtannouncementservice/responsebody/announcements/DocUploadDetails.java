package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocUploadDetails {
    private Long fileUuid;
    private String fileName;
    private String fileType;
    private String type;
}
