package com.nse.listingcompliance.lcdebtannouncementservice.proxy;


import com.nse.listingcompliance.lcdebtannouncementservice.model.dms.CreateFolder;
import com.nse.listingcompliance.lcdebtannouncementservice.model.dms.DmsIndex;
import com.nse.listingcompliance.lcdebtannouncementservice.model.dms.DocumentResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.model.dms.NGOAddDocumentResponseBDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(name = "dms-service",  url = "${dms-service.dmsBaseUrl:https://dms-service.apps.dev1.nseparivartan.com/api/v1}")
public interface DMSService {

    @PostMapping(value = "/dmsintegration/document")
    ResponseEntity<DocumentResponse> downloadDocument(@RequestBody DmsIndex docIndex);

    @PostMapping(value = "/dmsintegration/document/new", consumes = { "multipart/form-data" })
    ResponseEntity<NGOAddDocumentResponseBDO> upload(@RequestPart("file") MultipartFile file,
                                                     @RequestPart("ngoAddDocumentBDO") String ngoAddDocumentBDO);

    @PostMapping("/docwrapper/folder")
    ResponseEntity<String> createFolder(@RequestBody CreateFolder createFolderRequest);
}
