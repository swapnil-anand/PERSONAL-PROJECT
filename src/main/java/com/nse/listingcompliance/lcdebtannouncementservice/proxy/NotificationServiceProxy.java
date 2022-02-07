////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: EmailResponse.java
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: NotificationServiceProxy
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

package com.nse.listingcompliance.lcdebtannouncementservice.proxy;

import com.nse.listingcompliance.lcdebtannouncementservice.model.notification.EmailNotificationContent;
import com.nse.listingcompliance.lcdebtannouncementservice.model.notification.EmailNotificationResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.model.notification.NotificationContent;
import com.nse.listingcompliance.lcdebtannouncementservice.model.notification.NotificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;



@FeignClient(name = "notification-service" ,  url = "${notification-service.dmsBaseUrl:https://notification-service.apps.dev1.nseparivartan.com/api/v1/notification}")
public interface NotificationServiceProxy {

    @PostMapping("/api/v1/notification/inapp/message")
    ResponseEntity<NotificationResponse> inAppNotification(@RequestBody NotificationContent notificationContent);


    @PostMapping("/email/")
    ResponseEntity<List<EmailNotificationResponse>> emailNotification(@RequestBody EmailNotificationContent emailNotificationContent);
}
