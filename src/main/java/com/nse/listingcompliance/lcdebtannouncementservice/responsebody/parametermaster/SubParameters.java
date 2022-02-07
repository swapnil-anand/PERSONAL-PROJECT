package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubParameters {
    private Long subParameterId;
    private String subParameterName;
    private Long parameterId;
}