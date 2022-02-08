package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdequacyWrongSubject {
    private Boolean wrongSubject;
    private LocalDateTime wrongSubjectDt;
    private List<String> wrongSubjectTo;
    private String wrongSubjectBody;
    private LocalDateTime responseReceivedDt;
    private Long noOfResponse;
}
