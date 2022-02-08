package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponse {
    private Long communicationId;
    private String response;
    private String responseStatus;
}
