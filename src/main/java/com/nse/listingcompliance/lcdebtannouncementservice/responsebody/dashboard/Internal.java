package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Internal {
    private String internalType;
    private LocalDateTime internalDt;
    private List<String> internalTo;
    private String internalEmailBody;
}
