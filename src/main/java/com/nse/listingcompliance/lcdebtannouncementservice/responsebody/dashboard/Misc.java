package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Misc {
    private List<String> genericSensitive;
    private List<String> subjectSensitive;
    private List<String> attachmentPdf;
}
