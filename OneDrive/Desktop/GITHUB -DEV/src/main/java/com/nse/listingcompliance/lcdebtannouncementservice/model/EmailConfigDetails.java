package com.nse.listingcompliance.lcdebtannouncementservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailConfigDetails {

    private List<String> emailList;
    private String quarterName;
    private String submissionEndDate;
}
