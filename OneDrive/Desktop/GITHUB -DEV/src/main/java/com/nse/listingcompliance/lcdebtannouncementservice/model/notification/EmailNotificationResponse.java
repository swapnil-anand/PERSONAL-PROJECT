package com.nse.listingcompliance.lcdebtannouncementservice.model.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class EmailNotificationResponse {

    private Long id;
    private String notServiceType;
    private String transId;
    private String traceId;
    private String spanId;
    private Timestamp sentDate;
    private String serviceId;
    private String callServiceName;
    private Timestamp modifiedDate;
    private Timestamp delSentRecieveDate;
    private String bulkId;
    private String otp;
    private String webhookUrl;
    private String messageId;
    private String reqMessage;
    private String resMessage;
    private String delReqMessage;
    private String delResMessage;
    private String status;
    private String to;
    private String cc;
    private String bcc;
    private String from;
    private String errorCode;
    private String errorMessage;
    private String comments;
}
