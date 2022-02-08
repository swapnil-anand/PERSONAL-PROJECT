package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster;


import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ParameterResponseBody {
    private List<Parameter> parameters;
    private List<SubParameters> subParameters;
}