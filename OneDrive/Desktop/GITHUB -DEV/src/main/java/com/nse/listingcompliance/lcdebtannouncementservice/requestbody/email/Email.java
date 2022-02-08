package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private Long communicationId;
    private String from;
    private List<String> to;
    private List<String> cc;
    private String subject;
    private String body;
    private String regulation;
    private Long attachmentId;
    private String emailType;
}
