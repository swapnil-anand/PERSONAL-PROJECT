package com.nse.listingcompliance.lcdebtannouncementservice.repository.ocr;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.ocr.OcrKeywords;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.ocrkeyword.OcrKeywordResponseBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;

public interface OcrAttachment extends JpaRepository<OcrKeywords, Long> {
    String OCR_RESPONSE = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.ocrkeyword.OcrKeywordResponseBody";
    @Query(
            "SELECT o " +
            "FROM OcrKeywords o " +
            "INNER JOIN o.ocrMasterId m " +
            "INNER JOIN m.announcement a " +
            "WHERE a.annId = :announcementUuid "
    )
    List<OcrKeywords> findById(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            value = "SELECT " + OCR_RESPONSE +
                    "(o.keyword, o.keywordType, o.pageNo) " +
                    "FROM OcrKeywords o " +
                    "INNER JOIN o.ocrMasterId m " +
                    "INNER JOIN m.announcement a " +
                    "WHERE a.annId = :annId AND o.keywordType = :keywordType"
    )
    List<OcrKeywordResponseBody> findAllByAnnouncement(@Param(value = "annId") String annId, @Param(value = "keywordType") String keywordType);
}
