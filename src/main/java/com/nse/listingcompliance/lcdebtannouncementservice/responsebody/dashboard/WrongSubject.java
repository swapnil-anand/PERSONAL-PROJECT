package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WrongSubject {
    private String wrongSubjectType;
    private LocalDateTime wrongSubjectDt;
    private List<String> wrongSubjectTo;
    private String wrongSubjectEmailBody;
    private LocalDateTime responseReceivedDt;
    private Long noOfResponse;
}
