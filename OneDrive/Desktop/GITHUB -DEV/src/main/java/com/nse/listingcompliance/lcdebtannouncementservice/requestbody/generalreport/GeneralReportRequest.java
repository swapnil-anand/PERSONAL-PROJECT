package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.generalreport;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeneralReportRequest {
    private String to;
    private String from;
    private List<Long> categoryId;
    private List<Long> subjectId;
    private List<String> columns;
}

