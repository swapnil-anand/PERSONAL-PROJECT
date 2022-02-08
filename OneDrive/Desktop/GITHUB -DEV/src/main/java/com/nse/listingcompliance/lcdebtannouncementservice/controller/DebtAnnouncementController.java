package com.nse.listingcompliance.lcdebtannouncementservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement.Announcement;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement.LockedStatus;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.remarks.RemarkCheck;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AnnouncementResponseBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AnnouncementStatus;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.initializes.CommonInitialize;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.ocrkeyword.OcrKeywordResponseBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.getRemarks;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtAnnouncementServiceInterface;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtOcrServiceInterface;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Api(value = "LC Debt Service", tags = "Announcement")
@Slf4j
public class DebtAnnouncementController {
    private DebtAnnouncementServiceInterface debtService;
    private DebtOcrServiceInterface ocrService;

    @Autowired
    public DebtAnnouncementController(DebtAnnouncementServiceInterface debtService, DebtOcrServiceInterface ocrService) {
        this.debtService = debtService;
        this.ocrService = ocrService;
    }

    @PostMapping("/announcements/{announcementUuid}/initialize")
    public ResponseEntity<Status> announcementInitialize(@PathVariable(value = "announcementUuid") String announcementUuid, @RequestHeader Map<String, String> header) throws CommonException, JsonProcessingException {
        return this.debtService.initializeAnnouncement(announcementUuid, header);
    }

    @PutMapping("/announcements")
    public ResponseEntity<List<AnnouncementStatus>> announcementSubmit(@RequestBody Announcement ann, @RequestHeader Map<String, String> header) {
        return this.debtService.updateAnnouncements(ann, header);
    }

    @GetMapping("/announcement/{announcementUuid}")
    public ResponseEntity<AnnouncementResponseBody> getAnnouncementDetails(@PathVariable("announcementUuid") String announcementId, @RequestHeader Map<String, String> header) {
        return this.debtService.getAnnouncementById(announcementId, header);
    }

    @GetMapping("/announcements/{announcementUuid}/remarks")
    public ResponseEntity<List<getRemarks>> getReviews(@PathVariable("announcementUuid") String announcementUuid){
        return this.debtService.getReviews(announcementUuid);
    }

    @GetMapping("/announcements/{announcementUuid}/status")
    public ResponseEntity<Status> getStatus(@PathVariable("announcementUuid") String announcementUuid) {
        return this.debtService.getRemarkStatus(announcementUuid);
    }

    @GetMapping("/announcements/{announcementUuid}/keywords")
    public ResponseEntity<ArrayList<OcrKeywordResponseBody>> getOcrKeywords(@PathVariable(value = "announcementUuid") String announcementUuid) {
        return this.ocrService.getOcrKeywords(announcementUuid);
    }

    @PutMapping("/announcements/{announcementUuid}/status")
    public ResponseEntity<Status> setStatus(@PathVariable(value = "announcementUuid") String announcementUuid,
                                            @RequestBody LockedStatus lockedStatus,
                                            @RequestHeader Map<String, String> header){
        return this.debtService.lockAnnouncement(announcementUuid, lockedStatus.getStatus(), header);
    }

    @PutMapping("/announcements/{announcementUuid}/remark")
    public ResponseEntity<CommonInitialize> checkForVerification(@PathVariable(value = "announcementUuid") String announcementUuid,
                                                                 @RequestBody RemarkCheck remark,
                                                                 @RequestHeader Map<String, String> header){
        return this.debtService.checkAndUpdateVerification(announcementUuid, remark, header);
    }
}
