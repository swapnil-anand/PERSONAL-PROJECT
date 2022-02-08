package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {
    private Long subjectId;
    private String subjectName;
    private String announcementText;
    private Long categoryId;
}
