package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.ocr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {
    private List<String> keyword;
    private Long page;
}
