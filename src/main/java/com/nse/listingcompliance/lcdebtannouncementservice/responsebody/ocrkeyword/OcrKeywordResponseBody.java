package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.ocrkeyword;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OcrKeywordResponseBody {
    private String keyword;
    private String keywordType;
    private Long pageNo;
}
