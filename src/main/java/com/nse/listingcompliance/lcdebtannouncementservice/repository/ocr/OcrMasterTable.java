package com.nse.listingcompliance.lcdebtannouncementservice.repository.ocr;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.ocr.OcrMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcrMasterTable extends JpaRepository<OcrMaster, Long> {
}
