package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDataFields {
    private String fieldsToDisplay;
    private String fieldType;
    private String fieldsVal;
}
