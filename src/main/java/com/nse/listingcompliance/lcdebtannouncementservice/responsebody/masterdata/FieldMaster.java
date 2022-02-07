package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldMaster {
    private Long subjectId;
    private Long id;
    private String fieldDisplay;
    private String fieldType;
    private String fieldValue;
    private String isinOnlyField;
}
