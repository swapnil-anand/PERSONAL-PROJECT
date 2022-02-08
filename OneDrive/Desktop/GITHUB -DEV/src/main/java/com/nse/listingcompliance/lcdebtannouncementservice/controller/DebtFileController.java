package com.nse.listingcompliance.lcdebtannouncementservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;
import com.nse.listingcompliance.lcdebtannouncementservice.model.dms.Document;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement.AttachmentStatus;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AttachmentList;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.DocUploadDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtAnnouncementServiceInterface;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtFileServiceInterface;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtOcrServiceInterface;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Api(value = "LC Debt Service", tags = "Files")
@Slf4j
public class DebtFileController {
    private DebtFileServiceInterface debtService;
    private DebtAnnouncementServiceInterface debtAnnouncementService;
    private DebtOcrServiceInterface ocrService;

    @Autowired
    public DebtFileController(DebtFileServiceInterface debtService, DebtAnnouncementServiceInterface debtAnnouncementService, DebtOcrServiceInterface ocrService) {
        this.debtService = debtService;
        this.debtAnnouncementService = debtAnnouncementService;
        this.ocrService = ocrService;
    }

    @GetMapping("/files")
    public ResponseEntity<List<AttachmentList>> getFiles(@RequestHeader Map<String,String> header){
        return this.debtAnnouncementService.getAllAttachmentListByUser(header);
    }

    @PostMapping("/files/add")
    public ResponseEntity<DocUploadDetails> saveFiles(@Valid @RequestPart(value = "attachmentType") String attachmentType,
                                                      @Valid @RequestPart(value = "announcementId") String announcementId,
                                                      @Valid @RequestPart(value = "id") String id,
                                                      @Valid @RequestPart(value = "file") MultipartFile file,
                                                      @RequestHeader Map<String, String> header) throws CommonException, JsonProcessingException {
        AttachmentStatus attachmentStatus = AttachmentStatus.builder()
                .attachmentType(attachmentType)
                .announcementId(announcementId)
                .id(Long.valueOf(id))
                .build();
        return this.debtAnnouncementService.uploadAttachment(attachmentStatus, file, header);
    }

    @DeleteMapping("/files/{fileId}")
    public String deleteFileById(@PathVariable(value = "fileId") String fileId) {
        return fileId;
    }

    @GetMapping("/files/{announcementUuid}/get")
    public ResponseEntity<Resource> getFileById(@PathVariable(value = "announcementUuid") String announcementUuid) throws CommonException {
        Document document = this.debtService.getDocument(announcementUuid);
        Resource resource = new ByteArrayResource(document.getFileData());
        final HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"");
        responseHeader.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity.ok().headers(responseHeader).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }

}
