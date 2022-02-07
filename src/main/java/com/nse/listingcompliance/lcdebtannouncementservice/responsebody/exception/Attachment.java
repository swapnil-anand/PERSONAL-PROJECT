package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment {
    private Long attachmentId;
    private String fileName;
}