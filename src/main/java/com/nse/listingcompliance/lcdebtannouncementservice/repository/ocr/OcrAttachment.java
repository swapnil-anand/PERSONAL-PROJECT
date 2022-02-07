package com.nse.listingcompliance.lcdebtannouncementservice.repository.ocr;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.ocr.OcrKeywords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.*;

public interface OcrAttachment extends JpaRepository<OcrKeywords, Long> {
    @Query(
            "SELECT o " +
            "FROM OcrKeywords o " +
            "INNER JOIN o.ocrMasterId m " +
            "INNER JOIN m.announcement a " +
            "WHERE a.annId = :announcementUuid "
    )
    List<OcrKeywords> findById(@Param(value = "announcementUuid") String announcementUuid);
}
