package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport;

import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.DocUploadDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.ocrkeyword.OcrKeywordResponseBody;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdequacyMisc {
    private List<OcrKeywordResponseBody> genericKeywords;
    private List<OcrKeywordResponseBody> subjectWise;
    private DocUploadDetails attachedFile;
}