package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MasterData {
    private List<Subject> subjects;
    private List<Category> category;
    private List<FieldMaster> isin;
    private List<FieldMaster> dataField;
    private List<FieldMaster> disclaimer;
    private List<SearchSubjectResponse> searchSubject;
}
