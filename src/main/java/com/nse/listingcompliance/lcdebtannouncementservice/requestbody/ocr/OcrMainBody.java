package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.ocr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OcrMainBody {
    private String execution_status;
    private Long execution_status_code;
    private OcrAnnouncementResponse ocr_announcements_response;
}
