package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LockedStatus {
    private Boolean status;
}
