package com.nse.listingcompliance.lcdebtannouncementservice.proxy;

import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.ocr.OcrMainBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "ocr-service",  url = "${ocr-service.BaseUrl:ocr-service.apps.dev1.nseparivartan.com/api/v1}")
public interface OcrService {

    @PostMapping(value = "/OCR/announcements", consumes = { "multipart/form-data" })
    ResponseEntity<OcrMainBody> upload(@RequestPart("file") MultipartFile file,
                                       @RequestPart("symbol") String symbol,
                                       @RequestPart("company_name") String company_name,
                                       @RequestPart("subject") String subject,
                                       @RequestPart("description") String description);

}