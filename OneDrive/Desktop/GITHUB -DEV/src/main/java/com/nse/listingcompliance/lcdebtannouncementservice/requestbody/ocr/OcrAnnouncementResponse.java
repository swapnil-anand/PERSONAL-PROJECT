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
public class OcrAnnouncementResponse {
    private String symbol;
    private String subject;
    private String description;
    private String summary;
    private String file_url;
    private String company_name;
    private Boolean pdf_was_scanned;

    private List<Keyword> subject_keywords_found;

    private List<String> subject_keywords_missed;

    private List<Keyword> negative_generic_keywords_found;

    private List<Keyword> negative_subject_keywords_found;

    private String text_excel;
    //private String announcement_id;
}
