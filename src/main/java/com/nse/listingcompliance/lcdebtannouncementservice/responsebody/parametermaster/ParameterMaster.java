package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ParameterMaster {
        private List<Parameter> parameters;
        private List<SubParameter> subParameters;

}
