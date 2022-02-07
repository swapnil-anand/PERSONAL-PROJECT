package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster;

import lombok.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Parameter {
    private Long parameterId;
    private String parameterName;
}