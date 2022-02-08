package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.communication;

import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.DocUploadDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Communication {
    private Long id;
    private String emailSubject;
    private String emailBody;
    private String emailType;
    private String annId;
    private String status;
    private List<DocUploadDetails> attachmentDetails;
    private String commCreatedBy;
    private LocalDateTime commCreatedDt;
    private LocalDateTime responseDt;
    private String responseBy;
    private String response;
}