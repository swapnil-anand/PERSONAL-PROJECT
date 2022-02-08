package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discrepancy {
    private String applicationNumber;
    private String subjectType;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date submissionDate;
    private String clarificationStatus;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date clarificationReceivedDate;
    private String communicationType;
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date responseDate;

}
