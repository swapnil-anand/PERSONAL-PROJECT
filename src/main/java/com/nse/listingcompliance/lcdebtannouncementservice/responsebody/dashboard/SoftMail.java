package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SoftMail {
    private String softMailType;
    private LocalDateTime softMailDt;
    private List<String> emailTo;
    private String softMailBody;
}
