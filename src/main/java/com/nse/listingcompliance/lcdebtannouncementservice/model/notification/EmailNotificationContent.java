package com.nse.listingcompliance.lcdebtannouncementservice.model.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailNotificationContent {

    private String funcOrServiceID;
    private String funcOrServiceName;
    private String invocationType;
    private RequestEmailBody requestEmailBody;
    private String webHookURL;
    private String webHookURLFlagValue;

}
