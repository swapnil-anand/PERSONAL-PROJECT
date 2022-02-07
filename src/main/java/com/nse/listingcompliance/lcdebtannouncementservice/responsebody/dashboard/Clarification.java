package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Clarification {
    private String clarificationType;
    private List<String> emailTo;
    private String emailBody;
    private LocalDateTime responseReceivedDt;
    private Long noOfResponse;
}
