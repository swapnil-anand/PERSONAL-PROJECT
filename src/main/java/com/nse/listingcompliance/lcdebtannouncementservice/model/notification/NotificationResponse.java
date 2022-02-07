package com.nse.listingcompliance.lcdebtannouncementservice.model.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NotificationResponse {
    
    private String statusMessage;
    private int statusCode;
	@JsonInclude(value = Include.NON_NULL)
	private String inappTransId;
}
