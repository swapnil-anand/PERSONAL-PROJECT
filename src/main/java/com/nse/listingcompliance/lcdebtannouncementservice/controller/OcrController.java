package com.nse.listingcompliance.lcdebtannouncementservice.controller;

import com.nse.listingcompliance.lcdebtannouncementservice.proxy.OcrService;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.ocr.OcrMainBody;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin
@Api(value = "LC Debt Service", tags = "ocr")
@Slf4j
public class OcrController {

    private OcrService ocrServiceClient;

    @Autowired
    public OcrController(OcrService ocrServiceClient) {
        this.ocrServiceClient = ocrServiceClient;
    }

    @PostMapping("/ocr")
    public ResponseEntity<OcrMainBody> getResponse(@RequestParam("symbol") String symbol, @RequestParam("company_name") String company_name,
                                                   @RequestParam("subject") String subject, @RequestParam("description") String description,
                                                   @Valid @RequestPart(value = "file", required = false) MultipartFile file,
                                                   @RequestHeader Map<String, String> header) {
        return this.ocrServiceClient.upload(file, symbol, company_name, subject, description);
    }
}
