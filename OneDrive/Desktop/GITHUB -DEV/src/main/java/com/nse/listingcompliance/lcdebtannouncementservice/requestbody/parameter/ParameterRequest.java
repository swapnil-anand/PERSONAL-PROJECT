package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.parameter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterRequest {
    private String action;
    private String announcementId;
    private List<Parameter> parameter;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Parameter {
        private Long parameterId;
        private List<Long> subParameterId;
    }
}
