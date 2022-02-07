package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {
    private String parameterName;
    private List<String> subParameter;
}
