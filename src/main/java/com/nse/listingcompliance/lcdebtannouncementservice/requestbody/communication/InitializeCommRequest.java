package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.communication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InitializeCommRequest {
    private String announcementUuid;
    private String communicationType;
    private Long communicationId;
    private String communicationFrom;
}
