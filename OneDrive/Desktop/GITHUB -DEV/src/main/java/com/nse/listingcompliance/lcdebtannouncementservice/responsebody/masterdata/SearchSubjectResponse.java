package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchSubjectResponse {
    private Long id;
    private String subjectName;
    private Long subjectId;
    private Long masterFieldId;
    private String value;
}
