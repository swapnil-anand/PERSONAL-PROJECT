package com.nse.listingcompliance.lcdebtannouncementservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateToQuarter {
    private String quarterName;
    private Date quarterStartDate;
    private Date quarterEndDate;
}
