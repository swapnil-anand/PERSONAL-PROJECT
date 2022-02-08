package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SubParameter {
        private Long subParameterId;
        private String subParameterName;
        private Long parameterId;
}