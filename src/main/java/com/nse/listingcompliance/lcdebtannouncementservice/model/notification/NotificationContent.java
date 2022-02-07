////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: NotificationContent.java
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: Prabhakar
//
// NSE - Confidential
// Do not use, distribute, or copy without consent of NSE.
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// Copyright (c) 2020 NSE. All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.nse.listingcompliance.lcdebtannouncementservice.model.notification;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class NotificationContent {	
	
	@ApiModelProperty(value = "User Id ", example = "UserId11")
	private String notificationUserId;
	
	@ApiModelProperty(value = "Notification Type ", example = "broadcastmessage/usermessage/groupmessage")
	private String notificationType;	
	
	@ApiModelProperty(value = "Name of Caller Service ", example = "LST")
	private String serviceName;
	
	@ApiModelProperty(value = "Notification Title ", example = "NSE-PAYMENT")
	private String notificationTitle;
	
	@ApiModelProperty(value = "Notification message to send ", example = "hi Please pay your subscription")
	private String notificationMsgToSend;
	
	@ApiModelProperty(value = "Notification is actionable ", example = "Y/N")
	private String notificationActionable;
	
	@ApiModelProperty(value = "Notification actions ", example = "{test:}")
	private Map<String,Object> notificationAction;
	
	
	@ApiModelProperty(value = "Notification Start Time ", example = "2020-OCT-28 19:05:34 IST")
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MMM-dd HH:mm:ss z", timezone="IST")
	private Date notificationStartTime;	
	
	
	@ApiModelProperty(value = "Notification Send Time ", example = "2020-OCT-28 19:05:34 IST")
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MMM-dd HH:mm:ss z", timezone="IST")
	private Date notificationSendTimestamp;	
	
	@ApiModelProperty(value = "Notification units ", example = "Minute/Hour/Day/Month")
	private String notificationTtlUnit;	
	
	@ApiModelProperty(value = "Notification units ", example = "3")
	private String notificationTtl;	
	
	@ApiModelProperty(value = "Notification Reoccurence units ", example = "Minute/Hour/Day/Month")
	private String notificationReoccTtlUnit;	
	
	@ApiModelProperty(value = "Notification Reoccurence units ", example = "3")
	private String notificationReoccTtl;	
	
	@ApiModelProperty(value = "Notification Reoccurence count ", example = "3")
	private String notificationReoccCount;		
	
	@ApiModelProperty(value = "Notification Comments ", example = "Comments")
	private String comments;
	}