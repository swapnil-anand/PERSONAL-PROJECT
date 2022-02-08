package com.nse.listingcompliance.lcdebtannouncementservice.model.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestEmailBody {

    private String bulkId;
    private List<String> cc;
    private List<String> bcc;
    private String from;
    private String html;
    private String intermediateReport;
    private String replyTo;
    private String subject;
    private int templateId;
    private String text;
    private List<String> to;
}
